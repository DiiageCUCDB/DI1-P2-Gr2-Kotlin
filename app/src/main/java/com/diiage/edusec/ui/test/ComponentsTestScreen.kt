package com.diiage.edusec.ui.test

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diiage.edusec.ui.components.*
import com.diiage.edusec.ui.components.input.PrimaryButton
import com.diiage.edusec.ui.components.input.PrimaryTextField
import com.diiage.edusec.ui.components.input.SearchBar
import com.diiage.edusec.ui.components.input.ThemeSwitcher
import com.diiage.edusec.ui.components.navigation.BottomNavBar

@Composable
fun ComponentsTestScreen(navController: NavController) {
    var themeUpdateTrigger by remember { mutableStateOf(0) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var textFieldValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Theme Switcher
            Text(
                text = "Theme Switcher",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ThemeSwitcher(
                onThemeChanged = { themeUpdateTrigger++ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Search Bar
            Text(
                text = "Search Bar",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            SearchBar(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = "Rechercher un composant..."
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Primary Text Field
            Text(
                text = "Primary Text Field",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            PrimaryTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = "Entrez du texte"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Primary Buttons
            Text(
                text = "Primary Buttons",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            PrimaryButton(
                onClick = { /* Handle click */ },
                text = "Bouton Principal"
            )
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton(
                onClick = { /* Handle click */ },
                text = "Bouton Désactivé",
                enabled = false
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Challenge Cards
            Text(
                text = "Challenge Cards",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Score Rows
            Text(
                text = "Score Rows",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ScoreRow(rank = 1, name = "Alice Martin", score = 1500)
            ScoreRow(rank = 2, name = "Bob Wilson", score = 1420)
            ScoreRow(rank = 3, name = "Charlie Brown", score = 1380)
            ScoreRow(rank = 4, name = "Diana Prince", score = 1250)

            Spacer(modifier = Modifier.height(24.dp))

            // Group Items
            Text(
                text = "Group Items",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Test Button
            PrimaryButton(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("components_test") { inclusive = true }
                    }
                },
                text = "Retour à la connexion"
            )
        }
    }

    // Settings Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Paramètres") },
            text = { Text("Ceci est une démonstration de la boîte de dialogue.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}