package com.diiage.edusec.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Blue500,
    onPrimary = White,
    primaryContainer = Blue200,
    onPrimaryContainer = Blue900,

    secondary = Yellow500,
    onSecondary = Neutral1000,
    secondaryContainer = Yellow200,
    onSecondaryContainer = Yellow900,

    tertiary = Sky500,
    onTertiary = Neutral1000,
    tertiaryContainer = Sky200,
    onTertiaryContainer = Sky900,

    background = Neutral0,
    onBackground = Neutral1000,

    surface = Neutral100,
    onSurface = Neutral1000,

    surfaceVariant = Neutral200,
    onSurfaceVariant = Neutral700, // Lighter gray for light theme

    outline = Neutral400,
    outlineVariant = Neutral300,

    error = Color(0xFFBA1A1A),
    onError = White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue700,
    onPrimary = Neutral100,
    primaryContainer = Blue800,
    onPrimaryContainer = Blue200,

    secondary = Yellow600,
    onSecondary = Neutral1000,
    secondaryContainer = Yellow800,
    onSecondaryContainer = Yellow200,

    tertiary = Sky600,
    onTertiary = Neutral1000,
    tertiaryContainer = Sky800,
    onTertiaryContainer = Sky200,

    background = Color(0xFF121212),
    onBackground = Neutral100,

    surface = Neutral900,
    onSurface = Neutral100,

    surfaceVariant = Neutral800,
    onSurfaceVariant = Neutral400, // Lighter gray for dark theme

    outline = Neutral700,
    outlineVariant = Neutral800,

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)

@Composable
fun EduSecTheme(
    content: @Composable () -> Unit
) {
    val useDarkTheme = ThemeManager.isDarkTheme()

    val colorScheme = when {
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EdusecTypography,
        content = content
    )
}