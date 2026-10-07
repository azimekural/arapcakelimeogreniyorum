package com.azimut.arapcakelimeogreniyorum.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Assessment
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Style
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

/**
 * Navigation 3 serializable screen routes for application.
 */
sealed interface NavRoute {
    @Serializable
    data object Dashboard : NavRoute

    @Serializable
    data object AlphabetGuide : NavRoute

    @Serializable
    data object Flashcards : NavRoute

    @Serializable
    data object DailyQuiz : NavRoute

    @Serializable
    data object PlacementTest : NavRoute
}

/**
 * Bottom navigation bar item model.
 */
enum class AppBottomTab(
    val title: String,
    val route: NavRoute,
    val icon: ImageVector
) {
    DASHBOARD("Ana Sayfa", NavRoute.Dashboard, Icons.Rounded.Home),
    ALPHABET("Elif-Ba", NavRoute.AlphabetGuide, Icons.AutoMirrored.Rounded.MenuBook),
    FLASHCARDS("Kartlar", NavRoute.Flashcards, Icons.Rounded.Style),
    DAILY_QUIZ("Günün Testi", NavRoute.DailyQuiz, Icons.Rounded.SportsEsports),
    PLACEMENT_TEST("Seviye Testi", NavRoute.PlacementTest, Icons.Rounded.Assessment)
}
