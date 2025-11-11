package com.diiage.edusec.ui.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diiage.edusec.ui.components.input.PrimaryButton
import com.diiage.edusec.ui.components.input.ThemeSwitcher
import com.diiage.edusec.ui.components.layout.*

@Composable
fun HomeScreen(navController: NavController) {
    var themeUpdateTrigger by remember { mutableStateOf(0) }

    CenteredBox(horizontalPadding = 16.dp) {
        CenteredColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenue dans EduSec!",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            ExtraLargeSpacer()

            ThemeSwitcher(
                onThemeChanged = { themeUpdateTrigger++ }
            )

            ExtraLargeSpacer()

            PrimaryButton(
                onClick = {
                    navController.navigate("components_test")
                },
                text = "Tester les Composants"
            )

            MediumSpacer()

            PrimaryButton(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                text = "Retour à la connexion"
            )
        }
    }
}