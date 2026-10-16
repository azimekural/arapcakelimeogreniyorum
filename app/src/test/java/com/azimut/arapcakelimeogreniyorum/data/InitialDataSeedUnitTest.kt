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

        // Verify explicit IDs and letter uniqueness
        val ids = alphabet.map { it.id }
        assertEquals(28, ids.toSet().size)
        assertTrue(ids.all { it > 0 })

        val uniqueLetters = alphabet.map { it.letterArabic }.toSet()
        assertEquals(28, uniqueLetters.size)
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

        // Verify explicit IDs
        val ids = diacritics.map { it.id }
        assertEquals(diacritics.size, ids.toSet().size)
        assertTrue(ids.all { it > 0 })
    }

    @Test
    fun testVocabularySeedData_containsSpecificRequiredItems() {
        val vocab = InitialDataSeed.vocabularyList
        assertTrue(vocab.isNotEmpty())

        // Verify explicit IDs
        val ids = vocab.map { it.id }
        assertEquals(vocab.size, ids.toSet().size)
        assertTrue(ids.all { it > 0 })

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

        // Verify explicit IDs
        val ids = questions.map { it.id }
        assertEquals(questions.size, ids.toSet().size)
        assertTrue(ids.all { it > 0 })

        val placementQuestions = questions.filter { it.quizType == "PLACEMENT" }
        assertTrue(placementQuestions.size >= 5)

        val dailyQuestions = questions.filter { it.quizType == "DAILY" }
        assertTrue(dailyQuestions.isNotEmpty())
    }
}
