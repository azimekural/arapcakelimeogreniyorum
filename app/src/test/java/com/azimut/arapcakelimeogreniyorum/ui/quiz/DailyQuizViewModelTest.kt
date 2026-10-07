package com.azimut.arapcakelimeogreniyorum.ui.quiz

import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DailyQuizViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeRepository: FakeArabicLearningRepository
    private lateinit var viewModel: DailyQuizViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeArabicLearningRepository()
        viewModel = DailyQuizViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialState_loadsQuestionsCorrectly() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(3, state.heartsRemaining)
        assertEquals(0, state.score)
        assertEquals(0, state.totalXpGained)
        assertEquals(QuizStatus.IN_PROGRESS, state.quizStatus)
        assertTrue("Questions should not be empty", state.questions.isNotEmpty())

        collectorJob.cancel()
    }

    @Test
    fun testSelectOption_correctAnswer_incrementsScoreAndXp() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        val currentQ = viewModel.uiState.value.currentQuestion
        assertNotNull("Current question should not be null", currentQ)

        val correctIndex = currentQ!!.correctOptionIndex
        viewModel.selectOption(correctIndex)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isAnswerSubmitted)
        assertEquals(true, state.isCorrect)
        assertEquals(1, state.score)
        assertEquals(1, state.comboStreak)
        assertEquals(10, state.totalXpGained)
        assertEquals(3, state.heartsRemaining)

        collectorJob.cancel()
    }

    @Test
    fun testSelectOption_wrongAnswer_decrementsHeartsAndResetsCombo() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        val currentQ = viewModel.uiState.value.currentQuestion
        assertNotNull("Current question should not be null", currentQ)

        val wrongIndex = (currentQ!!.correctOptionIndex + 1) % 4
        viewModel.selectOption(wrongIndex)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isAnswerSubmitted)
        assertEquals(false, state.isCorrect)
        assertEquals(0, state.score)
        assertEquals(0, state.comboStreak)
        assertEquals(0, state.totalXpGained)
        assertEquals(2, state.heartsRemaining)

        collectorJob.cancel()
    }

    @Test
    fun testHeartsExhausted_triggersGameOver() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        // Lose 3 hearts
        repeat(3) {
            val currentQ = viewModel.uiState.value.currentQuestion
            assertNotNull("Current question should not be null", currentQ)
            val wrongIndex = (currentQ!!.correctOptionIndex + 1) % 4
            viewModel.selectOption(wrongIndex)
            advanceUntilIdle()

            if (it < 2) {
                viewModel.nextQuestion()
                advanceUntilIdle()
            }
        }

        val state = viewModel.uiState.value
        assertEquals(0, state.heartsRemaining)
        assertEquals(QuizStatus.GAME_OVER, state.quizStatus)

        collectorJob.cancel()
    }

    @Test
    fun testCompletingQuiz_triggersSuccessAndSavesProgress() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        val questionCount = viewModel.uiState.value.questions.size
        assertTrue("Questions size should be > 0", questionCount > 0)

        repeat(questionCount) {
            val currentQ = viewModel.uiState.value.currentQuestion
            val correctIndex = currentQ!!.correctOptionIndex
            viewModel.selectOption(correctIndex)
            advanceUntilIdle()

            viewModel.nextQuestion()
            advanceUntilIdle()
        }

        val state = viewModel.uiState.value
        assertEquals(QuizStatus.SUCCESS, state.quizStatus)
        assertEquals(questionCount, state.score)

        // Verify repository saved user progress
        val savedList = fakeRepository.savedProgress
        assertTrue(savedList.any { it.itemType == "QUIZ" })
        assertTrue(savedList.any { it.itemType == "USER_STATS" })
        assertTrue(savedList.any { it.itemType == "DAILY_STREAK" })

        collectorJob.cancel()
    }

    private class FakeArabicLearningRepository : ArabicLearningRepository {
        val savedProgress = mutableListOf<UserProgressEntity>()

        private val sampleQuestions = listOf(
            QuizQuestionEntity(
                id = 1,
                quizType = "DAILY",
                category = "ARABIC_TO_TURKISH",
                difficultyLevel = 1,
                questionText = "'كِتَاب' ne demektir?",
                questionArabic = "كِتَاب",
                optionA = "Kalem",
                optionB = "Kitap",
                optionC = "Masa",
                optionD = "Defter",
                correctOptionIndex = 1
            ),
            QuizQuestionEntity(
                id = 2,
                quizType = "DAILY",
                category = "TURKISH_TO_ARABIC",
                difficultyLevel = 1,
                questionText = "'Kalem' kelimesinin Arapçası nedir?",
                questionArabic = null,
                optionA = "قَلَم",
                optionB = "كِتَاب",
                optionC = "بَيْت",
                optionD = "مَسْجِد",
                correctOptionIndex = 0
            ),
            QuizQuestionEntity(
                id = 3,
                quizType = "DAILY",
                category = "VISUAL_MATCHING",
                difficultyLevel = 1,
                questionText = "Görseldeki kavramın Arapçası nedir?",
                questionArabic = "مَسْجِد",
                optionA = "مَسْجِد",
                optionB = "مَدْرَسَة",
                optionC = "بَيْت",
                optionD = "سُوق",
                correctOptionIndex = 0
            )
        )

        private val progressFlow = MutableStateFlow<List<UserProgressEntity>>(emptyList())
        private val questionsFlow = MutableStateFlow(sampleQuestions)

        override fun getAllProgress(): Flow<List<UserProgressEntity>> = progressFlow
        override fun getProgressByItem(itemType: String, itemId: Int): Flow<UserProgressEntity?> = MutableStateFlow(null)
        override fun getProgressByType(itemType: String): Flow<List<UserProgressEntity>> = MutableStateFlow(emptyList())

        override suspend fun saveUserProgress(progress: UserProgressEntity) {
            savedProgress.add(progress)
            progressFlow.value = savedProgress.toList()
        }

        override fun getCompletedCountByType(itemType: String): Flow<Int> = MutableStateFlow(0)
        override suspend fun resetAllProgress() { savedProgress.clear() }

        override fun getAllAlphabet(): Flow<List<AlphabetEntity>> = MutableStateFlow(emptyList())
        override fun getAlphabetById(id: Int): Flow<AlphabetEntity?> = MutableStateFlow(null)

        override fun getAllDiacritics(): Flow<List<DiacriticEntity>> = MutableStateFlow(emptyList())
        override fun getDiacriticById(id: Int): Flow<DiacriticEntity?> = MutableStateFlow(null)

        override fun getAllVocabulary(): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getVocabularyByCategory(category: String): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getVocabularyById(id: Int): Flow<VocabularyEntity?> = MutableStateFlow(null)
        override fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getAllVocabularyCategories(): Flow<List<String>> = MutableStateFlow(emptyList())
        override fun searchVocabulary(query: String): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {}
        override suspend fun updateMasteryLevel(id: Int, masteryLevel: Int) {}

        override fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>> = questionsFlow
        override fun getQuizQuestionsByType(quizType: String): Flow<List<QuizQuestionEntity>> = questionsFlow
        override fun getQuizQuestionsByCategory(category: String): Flow<List<QuizQuestionEntity>> = questionsFlow
        override fun getQuizQuestionsByDifficulty(difficulty: Int): Flow<List<QuizQuestionEntity>> = questionsFlow
        override fun getPlacementTestQuestions(): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())

        override suspend fun ensureDataSeeded() {}
    }
}
