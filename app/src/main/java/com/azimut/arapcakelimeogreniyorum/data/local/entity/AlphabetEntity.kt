package com.azimut.arapcakelimeogreniyorum.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing an Arabic alphabet letter with its various forms and details.
 */
@Entity(tableName = "alphabet")
data class AlphabetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderIndex: Int,
    val letterArabic: String,
    val nameTurkish: String,
    val transliteration: String,
    val isolatedForm: String,
    val initialForm: String,
    val medialForm: String,
    val finalForm: String,
    val description: String,
    val exampleWordArabic: String,
    val exampleWordTurkish: String,
    val audioResName: String
)
