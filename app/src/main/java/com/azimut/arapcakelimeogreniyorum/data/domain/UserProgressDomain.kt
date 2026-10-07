package com.azimut.arapcakelimeogreniyorum.data.domain

import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import java.util.Calendar

/**
 * User level definitions matching level progression titles.
 */
enum class UserLevel(
    val levelNumber: Int,
    val titleTurkish: String,
    val titleArabic: String,
    val minXp: Int,
    val maxXp: Int
) {
    MUBTEDI(1, "Mübtedi", "مُبْتَدِئ", 0, 99),
    TALIP(2, "Talip - Talebe", "طَالِب", 100, 299),
    MUALLIM(3, "Muallim - Öğretmen", "مُعَلِّم", 300, 599),
    ALIM(4, "Âlim - Bilgin", "عَالِم", 600, Int.MAX_VALUE);

    val fullDisplayTitle: String
        get() = "$titleTurkish ($titleArabic)"

    companion object {
        fun fromXp(xp: Int): UserLevel {
            return entries.lastOrNull { xp >= it.minXp } ?: MUBTEDI
        }

        fun fromPlacementScore(score: Int): UserLevel {
            return when {
                score >= 80 -> MUALLIM
                score >= 50 -> TALIP
                else -> MUBTEDI
            }
        }
    }
}

/**
 * Domain model summarizing user progress statistics.
 */
data class UserProgressStats(
    val totalXp: Int = 0,
    val currentLevel: UserLevel = UserLevel.MUBTEDI,
    val currentStreak: Int = 0,
    val bestQuizScore: Int = 0,
    val completedQuizzesCount: Int = 0,
    val lastQuizTimestamp: Long = 0L,
    val placementScore: Int? = null
) {
    val xpForNextLevel: Int
        get() = when (currentLevel) {
            UserLevel.MUBTEDI -> UserLevel.TALIP.minXp
            UserLevel.TALIP -> UserLevel.MUALLIM.minXp
            UserLevel.MUALLIM -> UserLevel.ALIM.minXp
            UserLevel.ALIM -> 1000
        }

    val xpProgressInLevelFraction: Float
        get() {
            val min = currentLevel.minXp
            val max = if (currentLevel == UserLevel.ALIM) 1000 else currentLevel.maxXp + 1
            val range = (max - min).coerceAtLeast(1)
            val currentInLevel = (totalXp - min).coerceAtLeast(0)
            return (currentInLevel.toFloat() / range.toFloat()).coerceIn(0f, 1f)
        }
}

/**
 * Utility helper functions for aggregating user progress entities from Room Database.
 */
object UserProgressCalculator {

    fun calculateStats(progressList: List<UserProgressEntity>): UserProgressStats {
        val quizProgresses = progressList.filter { it.itemType == "QUIZ" }
        val placementProgress = progressList.filter { it.itemType == "PLACEMENT_TEST" }.maxByOrNull { it.lastReviewedAt }
        val streakEntry = progressList.firstOrNull { it.itemType == "DAILY_STREAK" }
        val xpEntry = progressList.firstOrNull { it.itemType == "USER_STATS" }
        val bestScoreEntry = progressList.firstOrNull { it.itemType == "BEST_QUIZ_SCORE" }

        val calculatedTotalXpFromQuizzes = quizProgresses.sumOf { it.reviewCount }
        val storedXp = xpEntry?.score ?: 0
        val totalXp = maxOf(storedXp, calculatedTotalXpFromQuizzes)

        val completedQuizzes = quizProgresses.count { it.isCompleted }
        val calculatedBestScore = (quizProgresses.map { it.score } + listOf(bestScoreEntry?.score ?: 0)).maxOrNull() ?: 0

        val placementScore = placementProgress?.score
        val levelFromXp = UserLevel.fromXp(totalXp)
        val levelFromPlacement = placementScore?.let { UserLevel.fromPlacementScore(it) } ?: UserLevel.MUBTEDI

        // Level is higher of XP level or calibrated placement level
        val level = if (levelFromXp.levelNumber >= levelFromPlacement.levelNumber) levelFromXp else levelFromPlacement

        val streak = streakEntry?.score ?: 0
        val lastTimestamp = streakEntry?.lastReviewedAt ?: 0L

        return UserProgressStats(
            totalXp = totalXp,
            currentLevel = level,
            currentStreak = streak,
            bestQuizScore = calculatedBestScore,
            completedQuizzesCount = completedQuizzes,
            lastQuizTimestamp = lastTimestamp,
            placementScore = placementScore
        )
    }

    fun calculateUpdatedStreak(lastTimestamp: Long, currentStreak: Int, currentTime: Long): Int {
        if (lastTimestamp <= 0L) return 1

        val calLast = Calendar.getInstance().apply { timeInMillis = lastTimestamp }
        val calCurrent = Calendar.getInstance().apply { timeInMillis = currentTime }

        val sameYear = calLast.get(Calendar.YEAR) == calCurrent.get(Calendar.YEAR)
        val dayLast = calLast.get(Calendar.DAY_OF_YEAR)
        val dayCurrent = calCurrent.get(Calendar.DAY_OF_YEAR)

        return when {
            sameYear && dayCurrent == dayLast -> currentStreak.coerceAtLeast(1)
            sameYear && dayCurrent == dayLast + 1 -> currentStreak + 1
            else -> 1
        }
    }
}
