package com.azimut.arapcakelimeogreniyorum.data

import com.azimut.arapcakelimeogreniyorum.data.local.seed.InitialDataSeed
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class InitialDataSeedUnitTest {

    @Test
    fun testAlphabetSeedData_contains28Letters() {
        val alphabet = InitialDataSeed.alphabetList
        assertEquals(28, alphabet.size)

        // Verify first (Elif) and last (Ya)
        assertEquals("Elif", alphabet.first().nameTurkish)
        assertEquals("Ye", alphabet.last().nameTurkish)
    }

    @Test
    fun testDiacriticsSeedData_containsRequiredHarekeler() {
        val diacritics = InitialDataSeed.diacriticList
        assertTrue(diacritics.size >= 8)

        val names = diacritics.map { it.nameTurkish }
        assertTrue(names.any { it.contains("Üstün") })
        assertTrue(names.any { it.contains("Esre") })
        assertTrue(names.any { it.contains("Ötre") })
        assertTrue(names.any { it.contains("Cezm") })
        assertTrue(names.any { it.contains("Şedde") })
        assertTrue(names.any { it.contains("Tenvin") })
    }

    @Test
    fun testVocabularySeedData_containsSpecificRequiredItems() {
        val vocab = InitialDataSeed.vocabularyList
        assertTrue(vocab.isNotEmpty())

        // Check daily phrases with gender notes
        val maIsmuke = vocab.find { it.arabicText == "مَا اسْمُكَ؟" }
        assertNotNull(maIsmuke)
        assertEquals("Erkeklere hitap ederken", maIsmuke?.genderNote)

        val maIsmuki = vocab.find { it.arabicText == "مَا اسْمُكِ؟" }
        assertNotNull(maIsmuki)
        assertEquals("Kadınlara hitap ederken", maIsmuki?.genderNote)

        // Check prayer terms
        val ishaPrayer = vocab.find { it.transliteration == "Salâtü'l-İşâ" }
        assertNotNull(ishaPrayer)
        assertEquals("Yatsı Namazı", ishaPrayer?.turkishMeaning)

        // Check categories present
        val categories = vocab.map { it.category }.toSet()
        assertTrue(categories.contains("Daily Phrases"))
        assertTrue(categories.contains("Prayer Terms"))
        assertTrue(categories.contains("Everyday Objects"))
        assertTrue(categories.contains("Animals"))
        assertTrue(categories.contains("Numbers"))
    }

    @Test
    fun testQuizQuestionsSeedData_containsPlacementAndDailyQuizzes() {
        val questions = InitialDataSeed.quizQuestionList
        assertTrue(questions.isNotEmpty())

        val placementQuestions = questions.filter { it.quizType == "PLACEMENT" }
        assertTrue(placementQuestions.size >= 5)

        val dailyQuestions = questions.filter { it.quizType == "DAILY" }
        assertTrue(dailyQuestions.isNotEmpty())
    }
}
