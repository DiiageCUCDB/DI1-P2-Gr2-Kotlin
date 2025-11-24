package com.diiage.edusec.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.ui.core.components.input.PrimarySecondaryToggle
import com.diiage.edusec.ui.core.components.input.ThemeSwitcher
import com.diiage.edusec.ui.core.components.layout.MainScaffold

@Composable
fun SettingsScreen(navController: NavController) {
    var themeUpdateTrigger by remember { mutableStateOf(0) }

    MainScaffold(navController = navController) { _ ->
        Column {
            Text(
                text = "Paramètres",
                style = MaterialTheme.typography.displayLarge,
                fontSize = 52.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Text(
                        text = "Thème :",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 24.sp,
                        modifier = Modifier.alignByBaseline()
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    ThemeSwitcher(
                        onThemeChanged = { themeUpdateTrigger++ }
                    )

                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Text(
                        text = "Accessibilité :",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 24.sp,
                        modifier = Modifier.alignByBaseline()
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    PrimarySecondaryToggle(
                        isPrimarySelected = false,
                        onToggle = {}
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengesScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}