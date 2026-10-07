package com.azimut.arapcakelimeogreniyorum.data.repository

import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining data access operations for Arabic learning domain.
 */
interface ArabicLearningRepository {
    // Alphabet
    fun getAllAlphabet(): Flow<List<AlphabetEntity>>
    fun getAlphabetById(id: Int): Flow<AlphabetEntity?>

    // Diacritics
    fun getAllDiacritics(): Flow<List<DiacriticEntity>>
    fun getDiacriticById(id: Int): Flow<DiacriticEntity?>

    // Vocabulary
    fun getAllVocabulary(): Flow<List<VocabularyEntity>>
    fun getVocabularyByCategory(category: String): Flow<List<VocabularyEntity>>
    fun getVocabularyById(id: Int): Flow<VocabularyEntity?>
    fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>>
    fun getAllVocabularyCategories(): Flow<List<String>>
    fun searchVocabulary(query: String): Flow<List<VocabularyEntity>>
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)
    suspend fun updateMasteryLevel(id: Int, masteryLevel: Int)

    // Quiz
    fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>>
    fun getQuizQuestionsByType(quizType: String): Flow<List<QuizQuestionEntity>>
    fun getQuizQuestionsByCategory(category: String): Flow<List<QuizQuestionEntity>>
    fun getQuizQuestionsByDifficulty(difficulty: Int): Flow<List<QuizQuestionEntity>>
    fun getPlacementTestQuestions(): Flow<List<QuizQuestionEntity>>

    // User Progress
    fun getAllProgress(): Flow<List<UserProgressEntity>>
    fun getProgressByItem(itemType: String, itemId: Int): Flow<UserProgressEntity?>
    fun getProgressByType(itemType: String): Flow<List<UserProgressEntity>>
    suspend fun saveUserProgress(progress: UserProgressEntity)
    fun getCompletedCountByType(itemType: String): Flow<Int>
    suspend fun resetAllProgress()

    // Database Initialization
    suspend fun ensureDataSeeded()
}
