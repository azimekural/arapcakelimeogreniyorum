package com.azimut.arapcakelimeogreniyorum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a quiz or placement test question.
 */
@Entity(tableName = "quiz_questions")
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quizType: String, // "PLACEMENT", "DAILY", "ALPHABET", "VOCABULARY"
    val category: String?,
    val difficultyLevel: Int, // 1: Easy, 2: Medium, 3: Hard
    val questionText: String,
    val questionArabic: String? = null,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOptionIndex: Int, // 0 for A, 1 for B, 2 for C, 3 for D
    val explanation: String? = null
)
