package com.azimut.arapcakelimeogreniyorum.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.domain.UserLevel
import com.azimut.arapcakelimeogreniyorum.data.domain.UserProgressCalculator
import com.azimut.arapcakelimeogreniyorum.data.domain.UserProgressStats
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class QuizStatus {
    IN_PROGRESS,
    SUCCESS,
    GAME_OVER
}

enum class QuestionType {
    ARABIC_TO_TURKISH,
    TURKISH_TO_ARABIC,
    VISUAL_MATCHING,
    DIACRITIC_TRANSLITERATION
}

data class DailyQuizUiState(
    val questions: List<QuizQuestionEntity> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val heartsRemaining: Int = 3,
    val score: Int = 0,
    val totalXpGained: Int = 0,
    val comboStreak: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCorrect: Boolean? = null,
    val quizStatus: QuizStatus = QuizStatus.IN_PROGRESS,
    val selectedDifficulty: Int = 1,
    val userStats: UserProgressStats = UserProgressStats(),
    val isLoading: Boolean = false
) {
    val currentQuestion: QuizQuestionEntity?
        get() = if (questions.isNotEmpty() && currentQuestionIndex in questions.indices) questions[currentQuestionIndex] else null

    val isLastQuestion: Boolean
        get() = questions.isNotEmpty() && currentQuestionIndex == questions.size - 1

    val questionType: QuestionType
        get() {
            val q = currentQuestion ?: return QuestionType.ARABIC_TO_TURKISH
            val category = q.category ?: ""
            return when {
                category.contains("VISUAL", ignoreCase = true) -> QuestionType.VISUAL_MATCHING
                category.contains("TURKISH_TO_ARABIC", ignoreCase = true) -> QuestionType.TURKISH_TO_ARABIC
                category.contains("DIACRITIC", ignoreCase = true) ||
                category.contains("TRANSLITERATION", ignoreCase = true) -> QuestionType.DIACRITIC_TRANSLITERATION
                else -> QuestionType.ARABIC_TO_TURKISH
            }
        }
}

class DailyQuizViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    private val _currentQuestionIndex = MutableStateFlow(0)
    private val _heartsRemaining = MutableStateFlow(3)
    private val _score = MutableStateFlow(0)
    private val _totalXpGained = MutableStateFlow(0)
    private val _comboStreak = MutableStateFlow(0)
    private val _selectedOptionIndex = MutableStateFlow<Int?>(null)
    private val _isAnswerSubmitted = MutableStateFlow(false)
    private val _isCorrect = MutableStateFlow<Boolean?>(null)
    private val _quizStatus = MutableStateFlow(QuizStatus.IN_PROGRESS)
    private val _selectedDifficulty = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<DailyQuizUiState> = combine(
        combine(
            _currentQuestionIndex,
            _heartsRemaining,
            _score,
            _totalXpGained,
            _comboStreak
        ) { index, hearts, score, xpGained, combo ->
            SessionMetrics(index, hearts, score, xpGained, combo)
        },
        combine(
            _selectedOptionIndex,
            _isAnswerSubmitted,
            _isCorrect,
            _quizStatus,
            _selectedDifficulty
        ) { option, submitted, correct, status, difficulty ->
            AnswerState(option, submitted, correct, status, difficulty)
        },
        repository.getAllProgress(),
        repository.getQuizQuestionsByType("DAILY")
    ) { metrics, answerState, progressList, allDailyQuestions ->

        val stats = UserProgressCalculator.calculateStats(progressList)

        // Calibrate difficulty based on placement test result or user level if not explicitly overridden
        val calibratedDifficulty = answerState.difficulty ?: when (stats.currentLevel) {
            UserLevel.MUBTEDI -> 1
            UserLevel.TALIP -> 2
            UserLevel.MUALLIM, UserLevel.ALIM -> 3
        }

        // Filter questions by difficulty or fallback to all daily questions
        val filteredQuestions = allDailyQuestions.filter { it.difficultyLevel == calibratedDifficulty }
        val finalQuestions = if (filteredQuestions.isNotEmpty()) filteredQuestions else allDailyQuestions

        DailyQuizUiState(
            questions = finalQuestions,
            currentQuestionIndex = metrics.index.coerceIn(0, (finalQuestions.size - 1).coerceAtLeast(0)),
            heartsRemaining = metrics.hearts,
            score = metrics.score,
            totalXpGained = metrics.xpGained,
            comboStreak = metrics.combo,
            selectedOptionIndex = answerState.option,
            isAnswerSubmitted = answerState.submitted,
            isCorrect = answerState.correct,
            quizStatus = answerState.status,
            selectedDifficulty = calibratedDifficulty,
            userStats = stats,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DailyQuizUiState(isLoading = true)
    )

    fun selectOption(optionIndex: Int) {
        if (_isAnswerSubmitted.value || _quizStatus.value != QuizStatus.IN_PROGRESS) return

        _selectedOptionIndex.value = optionIndex
        _isAnswerSubmitted.value = true

        val currentQ = uiState.value.currentQuestion ?: return
        val isCorrect = optionIndex == currentQ.correctOptionIndex
        _isCorrect.value = isCorrect

        if (isCorrect) {
            _score.value += 1
            val newCombo = _comboStreak.value + 1
            _comboStreak.value = newCombo

            // +10 XP base + 5 XP streak bonus if combo >= 3
            val streakBonus = if (newCombo >= 3) 5 else 0
            val xpEarned = 10 + streakBonus
            _totalXpGained.value += xpEarned
        } else {
            _comboStreak.value = 0
            val remaining = _heartsRemaining.value - 1
            _heartsRemaining.value = remaining
            if (remaining <= 0) {
                _quizStatus.value = QuizStatus.GAME_OVER
            }
        }
    }

    fun nextQuestion() {
        if (_quizStatus.value != QuizStatus.IN_PROGRESS) return

        val state = uiState.value
        if (state.isLastQuestion) {
            _quizStatus.value = QuizStatus.SUCCESS
            saveQuizCompletion()
        } else {
            _selectedOptionIndex.value = null
            _isAnswerSubmitted.value = false
            _isCorrect.value = null
            _currentQuestionIndex.value += 1
        }
    }

    fun restartQuiz() {
        _currentQuestionIndex.value = 0
        _heartsRemaining.value = 3
        _score.value = 0
        _totalXpGained.value = 0
        _comboStreak.value = 0
        _selectedOptionIndex.value = null
        _isAnswerSubmitted.value = false
        _isCorrect.value = null
        _quizStatus.value = QuizStatus.IN_PROGRESS
    }

    fun setDifficulty(difficulty: Int) {
        _selectedDifficulty.value = difficulty
        restartQuiz()
    }

    private fun saveQuizCompletion() {
        viewModelScope.launch {
            val state = uiState.value
            val totalQuestions = state.questions.size.coerceAtLeast(1)
            val finalScorePercent = (state.score * 100) / totalQuestions
            val now = System.currentTimeMillis()

            // 1. Save Quiz Session Progress
            repository.saveUserProgress(
                UserProgressEntity(
                    itemType = "QUIZ",
                    itemId = (now % 100000).toInt(),
                    isCompleted = true,
                    score = finalScorePercent,
                    reviewCount = state.totalXpGained,
                    lastReviewedAt = now
                )
            )

            // 2. Update Persistent Total XP & Completed Quizzes Count
            val oldXp = state.userStats.totalXp
            val oldCompleted = state.userStats.completedQuizzesCount
            repository.saveUserProgress(
                UserProgressEntity(
                    itemType = "USER_STATS",
                    itemId = 1,
                    isCompleted = true,
                    score = oldXp + state.totalXpGained,
                    reviewCount = oldCompleted + 1,
                    lastReviewedAt = now
                )
            )

            // 3. Update Streak
            val newStreak = UserProgressCalculator.calculateUpdatedStreak(
                lastTimestamp = state.userStats.lastQuizTimestamp,
                currentStreak = state.userStats.currentStreak,
                currentTime = now
            )
            repository.saveUserProgress(
                UserProgressEntity(
                    itemType = "DAILY_STREAK",
                    itemId = 1,
                    isCompleted = true,
                    score = newStreak,
                    lastReviewedAt = now
                )
            )

            // 4. Update Best Quiz Score
            val currentBest = state.userStats.bestQuizScore
            if (finalScorePercent > currentBest) {
                repository.saveUserProgress(
                    UserProgressEntity(
                        itemType = "BEST_QUIZ_SCORE",
                        itemId = 1,
                        isCompleted = true,
                        score = finalScorePercent,
                        lastReviewedAt = now
                    )
                )
            }
        }
    }

    private data class SessionMetrics(
        val index: Int,
        val hearts: Int,
        val score: Int,
        val xpGained: Int,
        val combo: Int
    )

    private data class AnswerState(
        val option: Int?,
        val submitted: Boolean,
        val correct: Boolean?,
        val status: QuizStatus,
        val difficulty: Int?
    )

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DailyQuizViewModel::class.java)) {
                return DailyQuizViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
