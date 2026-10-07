package com.azimut.arapcakelimeogreniyorum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizQuestionDao {
    @Query("SELECT * FROM quiz_questions ORDER BY id ASC")
    fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE quizType = :quizType ORDER BY id ASC")
    fun getQuizQuestionsByType(quizType: String): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE category = :category ORDER BY id ASC")
    fun getQuizQuestionsByCategory(category: String): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE difficultyLevel = :difficulty ORDER BY id ASC")
    fun getQuizQuestionsByDifficulty(difficulty: Int): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE quizType = 'PLACEMENT' ORDER BY id ASC")
    fun getPlacementTestQuestions(): Flow<List<QuizQuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuizQuestionEntity>)

    @Query("SELECT COUNT(*) FROM quiz_questions")
    suspend fun getCount(): Int
}
