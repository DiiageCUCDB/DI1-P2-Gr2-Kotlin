package com.diiage.edusec.ui.challenges

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.domain.mock.mockChallenges
import com.diiage.edusec.ui.components.ChallengeCard
import com.diiage.edusec.ui.components.input.DifficultyFilter
import com.diiage.edusec.ui.components.input.DifficultyLevel
import com.diiage.edusec.ui.components.input.SearchBar
import com.diiage.edusec.ui.components.layout.MainScaffold
import java.time.LocalDate
import java.util.*

@Composable
fun ChallengesScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedDifficulties by remember {
        mutableStateOf<Set<DifficultyLevel>>(DifficultyLevel.values().toSet())
    }

    MainScaffold(navController = navController) { innerPadding ->
        Column(
            modifier = Modifier
                .padding()
                .fillMaxSize()
        ) {
            Text(
                text = "Challenges",
                style = MaterialTheme.typography.displayLarge,
                fontSize = 52.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // Search bar
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Rechercher...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Difficulty filter avec sélection multiple - CENTRÉ
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                DifficultyFilter(
                    selectedDifficulties = selectedDifficulties,
                    onDifficultySelected = { difficulty ->
                        selectedDifficulties = if (selectedDifficulties.contains(difficulty)) {
                            // Retirer la difficulté si déjà sélectionnée
                            selectedDifficulties - difficulty
                        } else {
                            // Ajouter la difficulté
                            selectedDifficulties + difficulty
                        }
                    },
                    modifier = Modifier.wrapContentWidth()
                )
            }

            // Filter challenges by search query and multiple difficulties
            val filteredChallenges = remember(searchQuery, selectedDifficulties) {
                // First filter by search
                val searchFiltered = if (searchQuery.text.isBlank()) {
                    mockChallenges
                } else {
                    mockChallenges.filter { challenge ->
                        challenge.name.lowercase(Locale.getDefault())
                            .contains(searchQuery.text.lowercase(Locale.getDefault())) ||
                                challenge.description.lowercase(Locale.getDefault())
                                    .contains(searchQuery.text.lowercase(Locale.getDefault()))
                    }
                }

                // Then filter by difficulties (show all if no difficulty selected)
                if (selectedDifficulties.isEmpty()) {
                    searchFiltered
                } else {
                    searchFiltered.filter { challenge ->
                        val challengeDifficultyLevel = when (challenge.difficulty) {
                            1, 2 -> DifficultyLevel.EASY
                            3 -> DifficultyLevel.MEDIUM
                            else -> DifficultyLevel.HARD
                        }
                        selectedDifficulties.contains(challengeDifficultyLevel)
                    }
                }
            }

            // List of challenges
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = filteredChallenges,
                    key = { it.id }
                ) { challenge ->
                    ChallengeCard(
                        title = challenge.name,
                        description = challenge.description,
                        date = LocalDate.now().plusDays(challenge.id.hashCode() % 30L),
                        difficulty = when (challenge.difficulty) {
                            1, 2 -> "Facile"
                            3 -> "Normal"
                            else -> "Difficile"
                        },
                        isEquipe = challenge.isGuildChallenge,
                        onClick = {
                            // Handle challenge click
                        }
                    )
                }

                // Show empty state if no challenges match the filters
                if (filteredChallenges.isEmpty()) {
                    item {
                        EmptyState(
                            searchQuery = searchQuery.text,
                            selectedDifficulties = selectedDifficulties,
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }
            }
        }
    }
}

// Empty state component when no challenges are found
@Composable
private fun EmptyState(
    searchQuery: String,
    selectedDifficulties: Set<DifficultyLevel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        val message = buildString {
            if (searchQuery.isNotBlank()) {
                append("Aucun défi trouvé pour \"$searchQuery\"")
                if (selectedDifficulties.isNotEmpty()) {
                    append(" avec les difficultés : ")
                    append(selectedDifficulties.joinToString(", ") { it.displayName })
                }
            } else if (selectedDifficulties.isNotEmpty()) {
                append("Aucun défi trouvé pour les difficultés : ")
                append(selectedDifficulties.joinToString(", ") { it.displayName })
            } else {
                append("Aucun défi trouvé")
            }
        }

        Text(
            text = message,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.material3.TextButton(
            onClick = { /* Reset filters - handled in parent */ }
        ) {
            Text(text = "Réinitialiser les filtres")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengesScreenPreview() {
    ChallengesScreen(navController = rememberNavController())
}