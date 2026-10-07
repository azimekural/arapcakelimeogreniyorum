package com.azimut.arapcakelimeogreniyorum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a vocabulary item, phrase, or term.
 */
@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val arabicText: String,
    val turkishMeaning: String,
    val transliteration: String,
    val category: String,
    val difficultyLevel: Int,
    val genderNote: String? = null,
    val imageResourceName: String? = null,
    val audioResourceName: String? = null,
    val pluralArabic: String? = null,
    val pluralTurkish: String? = null,
    val isFavorite: Boolean = false,
    val masteryLevel: Int = 0
)
