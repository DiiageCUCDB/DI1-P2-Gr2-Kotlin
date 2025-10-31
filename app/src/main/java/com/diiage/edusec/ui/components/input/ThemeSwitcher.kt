package com.diiage.edusec.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.theme.ThemeManager
import com.diiage.edusec.ui.theme.ThemeState

@Composable
fun ThemeSwitcher(
    modifier: Modifier = Modifier,
    onThemeChanged: (() -> Unit)? = null
) {
    val currentTheme = ThemeManager.themeState

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                ThemeManager.toggleTheme()
                onThemeChanged?.invoke()
            }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = when (currentTheme) {
                    ThemeState.Light -> "☀️"
                    ThemeState.Dark -> "🌙"
                    ThemeState.System -> "⚙️"
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = when (currentTheme) {
                    ThemeState.Light -> "Clair"
                    ThemeState.Dark -> "Sombre"
                    ThemeState.System -> "Système"
                },
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}