package com.azimut.arapcakelimeogreniyorum.ui.placement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PlacementTestUiState(
    val questions: List<QuizQuestionEntity> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val userAnswers: Map<Int, Int> = emptyMap(), // questionIndex -> selectedOptionIndex
    val isSubmitted: Boolean = false,
    val score: Int = 0,
    val totalQuestions: Int = 0,
    val evaluatedLevel: String = "",
    val isLoading: Boolean = false
) {
    val currentQuestion: QuizQuestionEntity?
        get() = if (questions.isNotEmpty() && currentQuestionIndex in questions.indices) questions[currentQuestionIndex] else null

    val isLastQuestion: Boolean
        get() = currentQuestionIndex == questions.size - 1
}

class PlacementTestViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    private val _currentIndex = MutableStateFlow(0)
    private val _userAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    private val _isSubmitted = MutableStateFlow(false)

    val uiState: StateFlow<PlacementTestUiState> = combine(
        _currentIndex,
        _userAnswers,
        _isSubmitted,
        repository.getPlacementTestQuestions()
    ) { index, answers, submitted, questions ->

        var correctCount = 0
        questions.forEachIndexed { i, q ->
            if (answers[i] == q.correctOptionIndex) {
                correctCount++
            }
        }

        val total = questions.size
        val calculatedScore = if (total > 0) (correctCount * 100) / total else 0

        val level = when {
            calculatedScore >= 80 -> "İleri Seviye (A2-B1)"
            calculatedScore >= 50 -> "Orta Seviye (A1-A2)"
            else -> "Başlangıç Seviyesi (Elif-Ba)"
        }

        PlacementTestUiState(
            questions = questions,
            currentQuestionIndex = index.coerceIn(0, (questions.size - 1).coerceAtLeast(0)),
            userAnswers = answers,
            isSubmitted = submitted,
            score = calculatedScore,
            totalQuestions = total,
            evaluatedLevel = level,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PlacementTestUiState(isLoading = true)
    )

    fun selectOption(questionIndex: Int, optionIndex: Int) {
        val updated = _userAnswers.value.toMutableMap()
        updated[questionIndex] = optionIndex
        _userAnswers.value = updated
    }

    fun nextQuestion() {
        val questions = uiState.value.questions
        if (_currentIndex.value < questions.size - 1) {
            _currentIndex.value += 1
        }
    }

    fun previousQuestion() {
        if (_currentIndex.value > 0) {
            _currentIndex.value -= 1
        }
    }

    fun submitTest() {
        _isSubmitted.value = true
        val score = uiState.value.score

        viewModelScope.launch {
            repository.saveUserProgress(
                UserProgressEntity(
                    itemType = "PLACEMENT_TEST",
                    itemId = 1,
                    isCompleted = true,
                    score = score,
                    lastReviewedAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun retakeTest() {
        _currentIndex.value = 0
        _userAnswers.value = emptyMap()
        _isSubmitted.value = false
    }

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlacementTestViewModel::class.java)) {
                return PlacementTestViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
