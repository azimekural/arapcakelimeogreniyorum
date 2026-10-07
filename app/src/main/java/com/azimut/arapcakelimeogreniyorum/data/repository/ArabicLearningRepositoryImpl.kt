package com.azimut.arapcakelimeogreniyorum.data.repository

import com.azimut.arapcakelimeogreniyorum.data.local.AppDatabase
import com.azimut.arapcakelimeogreniyorum.data.local.dao.AlphabetDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.DiacriticDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.QuizQuestionDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.UserProgressDao
import com.azimut.arapcakelimeogreniyorum.data.local.dao.VocabularyDao
import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository implementation connecting Room database DAOs to the application layer.
 */
class ArabicLearningRepositoryImpl(
    private val database: AppDatabase,
    private val alphabetDao: AlphabetDao = database.alphabetDao(),
    private val diacriticDao: DiacriticDao = database.diacriticDao(),
    private val vocabularyDao: VocabularyDao = database.vocabularyDao(),
    private val quizQuestionDao: QuizQuestionDao = database.quizQuestionDao(),
    private val userProgressDao: UserProgressDao = database.userProgressDao(),
) : ArabicLearningRepository {

    override fun getAllAlphabet(): Flow<List<AlphabetEntity>> =
        alphabetDao.getAllAlphabet()

    override fun getAlphabetById(id: Int): Flow<AlphabetEntity?> =
        alphabetDao.getAlphabetById(id)

    override fun getAllDiacritics(): Flow<List<DiacriticEntity>> =
        diacriticDao.getAllDiacritics()

    override fun getDiacriticById(id: Int): Flow<DiacriticEntity?> =
        diacriticDao.getDiacriticById(id)

    override fun getAllVocabulary(): Flow<List<VocabularyEntity>> =
        vocabularyDao.getAllVocabulary()

    override fun getVocabularyByCategory(category: String): Flow<List<VocabularyEntity>> =
        vocabularyDao.getVocabularyByCategory(category)

    override fun getVocabularyById(id: Int): Flow<VocabularyEntity?> =
        vocabularyDao.getVocabularyById(id)

    override fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>> =
        vocabularyDao.getFavoriteVocabulary()

    override fun getAllVocabularyCategories(): Flow<List<String>> =
        vocabularyDao.getAllCategories()

    override fun searchVocabulary(query: String): Flow<List<VocabularyEntity>> =
        vocabularyDao.searchVocabulary(query)

    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        vocabularyDao.updateFavoriteStatus(id, isFavorite)
    }

    override suspend fun updateMasteryLevel(id: Int, masteryLevel: Int) {
        vocabularyDao.updateMasteryLevel(id, masteryLevel)
    }

    override fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>> =
        quizQuestionDao.getAllQuizQuestions()

    override fun getQuizQuestionsByType(quizType: String): Flow<List<QuizQuestionEntity>> =
        quizQuestionDao.getQuizQuestionsByType(quizType)

    override fun getQuizQuestionsByCategory(category: String): Flow<List<QuizQuestionEntity>> =
        quizQuestionDao.getQuizQuestionsByCategory(category)

    override fun getQuizQuestionsByDifficulty(difficulty: Int): Flow<List<QuizQuestionEntity>> =
        quizQuestionDao.getQuizQuestionsByDifficulty(difficulty)

    override fun getPlacementTestQuestions(): Flow<List<QuizQuestionEntity>> =
        quizQuestionDao.getPlacementTestQuestions()

    override fun getAllProgress(): Flow<List<UserProgressEntity>> =
        userProgressDao.getAllProgress()

    override fun getProgressByItem(itemType: String, itemId: Int): Flow<UserProgressEntity?> =
        userProgressDao.getProgressByItem(itemType, itemId)

    override fun getProgressByType(itemType: String): Flow<List<UserProgressEntity>> =
        userProgressDao.getProgressByType(itemType)

    override suspend fun saveUserProgress(progress: UserProgressEntity) {
        userProgressDao.insertOrUpdate(progress)
    }

    override fun getCompletedCountByType(itemType: String): Flow<Int> =
        userProgressDao.getCompletedCountByType(itemType)

    override suspend fun resetAllProgress() {
        userProgressDao.resetAllProgress()
    }

    override suspend fun ensureDataSeeded() {
        database.ensureSeeded()
    }
}
