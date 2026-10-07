package com.azimut.arapcakelimeogreniyorum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing an Arabic diacritic (Hareke).
 */
@Entity(tableName = "diacritics")
data class DiacriticEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderIndex: Int,
    val nameArabic: String,
    val nameTurkish: String,
    val symbol: String,
    val explanation: String,
    val exampleWordArabic: String,
    val exampleWordTurkish: String,
    val soundType: String
)
