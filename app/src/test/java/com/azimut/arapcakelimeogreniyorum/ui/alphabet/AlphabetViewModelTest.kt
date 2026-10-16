package com.azimut.arapcakelimeogreniyorum.ui.alphabet

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
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlphabetViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeRepository: FakeArabicLearningRepository
    private lateinit var viewModel: AlphabetViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeArabicLearningRepository()
        viewModel = AlphabetViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testDiacritics_areDeduplicated() = runTest(testDispatcher) {
        val collectorJob = backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        val state = viewModel.uiState.value
        // Verify duplicate diacritics with same name are deduplicated
        assertEquals(2, state.diacritics.size)
        assertEquals("Üstün (Fatha)", state.diacritics[0].nameTurkish)
        assertEquals("Esre (Kasra)", state.diacritics[1].nameTurkish)

        collectorJob.cancel()
    }

    private class FakeArabicLearningRepository : ArabicLearningRepository {
        private val sampleDiacritics = listOf(
            DiacriticEntity(
                id = 1,
                orderIndex = 1,
                nameArabic = "فَتْحَة",
                nameTurkish = "Üstün (Fatha)",
                symbol = "َ",
                explanation = "A / E sound",
                exampleWordArabic = "كَتَبَ",
                exampleWordTurkish = "Kataba",
                soundType = "Vowel"
            ),
            DiacriticEntity(
                id = 2,
                orderIndex = 2,
                nameArabic = "فَتْحَة",
                nameTurkish = "Üstün (Fatha)", // Duplicate entry!
                symbol = "َ",
                explanation = "A / E sound duplicate",
                exampleWordArabic = "كَتَبَ",
                exampleWordTurkish = "Kataba",
                soundType = "Vowel"
            ),
            DiacriticEntity(
                id = 3,
                orderIndex = 3,
                nameArabic = "كَسْرَة",
                nameTurkish = "Esre (Kasra)",
                symbol = "ِ",
                explanation = "I / İ sound",
                exampleWordArabic = "شَرِبَ",
                exampleWordTurkish = "Shariba",
                soundType = "Vowel"
            )
        )

        override fun getAllAlphabet(): Flow<List<AlphabetEntity>> = MutableStateFlow(emptyList())
        override fun getAlphabetById(id: Int): Flow<AlphabetEntity?> = MutableStateFlow(null)

        override fun getAllDiacritics(): Flow<List<DiacriticEntity>> = MutableStateFlow(sampleDiacritics)
        override fun getDiacriticById(id: Int): Flow<DiacriticEntity?> = MutableStateFlow(null)

        override fun getAllVocabulary(): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getVocabularyByCategory(category: String): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getVocabularyById(id: Int): Flow<VocabularyEntity?> = MutableStateFlow(null)
        override fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override fun getAllVocabularyCategories(): Flow<List<String>> = MutableStateFlow(emptyList())
        override fun searchVocabulary(query: String): Flow<List<VocabularyEntity>> = MutableStateFlow(emptyList())
        override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {}
        override suspend fun updateMasteryLevel(id: Int, masteryLevel: Int) {}

        override fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())
        override fun getQuizQuestionsByType(quizType: String): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())
        override fun getQuizQuestionsByCategory(category: String): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())
        override fun getQuizQuestionsByDifficulty(difficulty: Int): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())
        override fun getPlacementTestQuestions(): Flow<List<QuizQuestionEntity>> = MutableStateFlow(emptyList())

        override fun getAllProgress(): Flow<List<UserProgressEntity>> = MutableStateFlow(emptyList())
        override fun getProgressByItem(itemType: String, itemId: Int): Flow<UserProgressEntity?> = MutableStateFlow(null)
        override fun getProgressByType(itemType: String): Flow<List<UserProgressEntity>> = MutableStateFlow(emptyList())
        override suspend fun saveUserProgress(progress: UserProgressEntity) {}
        override fun getCompletedCountByType(itemType: String): Flow<Int> = MutableStateFlow(0)
        override suspend fun resetAllProgress() {}

        override suspend fun ensureDataSeeded() {}
    }
}
