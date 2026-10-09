package com.azimut.arapcakelimeogreniyorum.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ParchmentLightColorScheme = lightColorScheme(
    primary = ParchmentGold,
    onPrimary = ParchmentTextLight,
    primaryContainer = ParchmentContainer,
    onPrimaryContainer = ManuscriptDeepBrown,
    secondary = ManuscriptBrown,
    onSecondary = ParchmentTextLight,
    secondaryContainer = ParchmentGoldLight,
    onSecondaryContainer = ManuscriptDeepBrown,
    tertiary = EmeraldManuscript,
    onTertiary = ParchmentTextWhite,
    background = ParchmentIvory,
    onBackground = ManuscriptDeepBrown,
    surface = ParchmentCardBg,
    onSurface = ManuscriptDeepBrown,
    surfaceVariant = ParchmentBeige,
    onSurfaceVariant = ManuscriptDeepBrown,
    outline = ParchmentGold,
    outlineVariant = ParchmentGoldLight,
)

private val ParchmentDarkColorScheme = darkColorScheme(
    primary = ParchmentGold,
    onPrimary = ParchmentTextLight,
    primaryContainer = ParchmentContainerDark,
    onPrimaryContainer = ParchmentTextLight,
    secondary = ParchmentGoldLight,
    onSecondary = ManuscriptDeepBrown,
    secondaryContainer = ManuscriptBrown,
    onSecondaryContainer = ParchmentTextLight,
    tertiary = EmeraldManuscript,
    onTertiary = ParchmentTextWhite,
    background = ParchmentDarkBg,
    onBackground = ParchmentDarkOnSurface,
    surface = ParchmentDarkSurface,
    onSurface = ParchmentDarkOnSurface,
    surfaceVariant = ParchmentCardBgDark,
    onSurfaceVariant = ParchmentDarkOnSurface,
    outline = ParchmentGold,
    outlineVariant = ParchmentGoldDark,
)

@Composable
fun ArapcakelimeogreniyorumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            if (darkTheme) ParchmentDarkColorScheme else ParchmentLightColorScheme
        }
        darkTheme -> ParchmentDarkColorScheme
        else -> ParchmentLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
