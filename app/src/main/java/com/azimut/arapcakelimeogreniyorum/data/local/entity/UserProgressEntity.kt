package com.azimut.arapcakelimeogreniyorum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing user progress for items, quizzes, or overall achievements.
 */
@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemType: String, // "ALPHABET", "DIACRITIC", "VOCABULARY", "QUIZ", "DAILY_STREAK", "PLACEMENT_TEST"
    val itemId: Int = 0,
    val isCompleted: Boolean = false,
    val score: Int = 0,
    val lastReviewedAt: Long = System.currentTimeMillis(),
    val reviewCount: Int = 0
)
