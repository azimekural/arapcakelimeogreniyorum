package com.azimut.arapcakelimeogreniyorum.data.domain

import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class UserProgressCalculatorTest {

    @Test
    fun testUserLevelFromXp_correctMapping() {
        assertEquals(UserLevel.MUBTEDI, UserLevel.fromXp(0))
        assertEquals(UserLevel.MUBTEDI, UserLevel.fromXp(50))
        assertEquals(UserLevel.TALIP, UserLevel.fromXp(100))
        assertEquals(UserLevel.TALIP, UserLevel.fromXp(250))
        assertEquals(UserLevel.MUALLIM, UserLevel.fromXp(300))
        assertEquals(UserLevel.MUALLIM, UserLevel.fromXp(550))
        assertEquals(UserLevel.ALIM, UserLevel.fromXp(600))
        assertEquals(UserLevel.ALIM, UserLevel.fromXp(1200))
    }

    @Test
    fun testUserLevelFromPlacementScore_correctCalibration() {
        assertEquals(UserLevel.MUBTEDI, UserLevel.fromPlacementScore(40))
        assertEquals(UserLevel.TALIP, UserLevel.fromPlacementScore(60))
        assertEquals(UserLevel.MUALLIM, UserLevel.fromPlacementScore(85))
    }

    @Test
    fun testCalculateStats_aggregatesProgressEntitiesCorrectly() {
        val now = System.currentTimeMillis()
        val mockProgressList = listOf(
            UserProgressEntity(itemType = "USER_STATS", itemId = 1, isCompleted = true, score = 350, reviewCount = 3),
            UserProgressEntity(itemType = "DAILY_STREAK", itemId = 1, isCompleted = true, score = 5, lastReviewedAt = now),
            UserProgressEntity(itemType = "BEST_QUIZ_SCORE", itemId = 1, isCompleted = true, score = 90),
            UserProgressEntity(itemType = "PLACEMENT_TEST", itemId = 1, isCompleted = true, score = 80, lastReviewedAt = now)
        )

        val stats = UserProgressCalculator.calculateStats(mockProgressList)

        assertEquals(350, stats.totalXp)
        assertEquals(5, stats.currentStreak)
        assertEquals(90, stats.bestQuizScore)
        assertEquals(UserLevel.MUALLIM, stats.currentLevel)
        assertEquals(80, stats.placementScore)
    }

    @Test
    fun testCalculateUpdatedStreak_sameDayAndConsecutiveDayLogic() {
        val calendar = Calendar.getInstance().apply {
            set(2025, Calendar.MAY, 10, 10, 0)
        }
        val day1Timestamp = calendar.timeInMillis

        // Same day -> streak remains same
        val sameDayStreak = UserProgressCalculator.calculateUpdatedStreak(
            lastTimestamp = day1Timestamp,
            currentStreak = 3,
            currentTime = day1Timestamp + (2 * 60 * 60 * 1000)
        )
        assertEquals(3, sameDayStreak)

        // Next day (May 11) -> streak increments by +1
        calendar.set(2025, Calendar.MAY, 11, 14, 0)
        val day2Timestamp = calendar.timeInMillis
        val nextDayStreak = UserProgressCalculator.calculateUpdatedStreak(
            lastTimestamp = day1Timestamp,
            currentStreak = 3,
            currentTime = day2Timestamp
        )
        assertEquals(4, nextDayStreak)

        // Skipped day (May 13) -> streak resets to 1
        calendar.set(2025, Calendar.MAY, 13, 14, 0)
        val day4Timestamp = calendar.timeInMillis
        val skippedStreak = UserProgressCalculator.calculateUpdatedStreak(
            lastTimestamp = day1Timestamp,
            currentStreak = 3,
            currentTime = day4Timestamp
        )
        assertEquals(1, skippedStreak)
    }
}
