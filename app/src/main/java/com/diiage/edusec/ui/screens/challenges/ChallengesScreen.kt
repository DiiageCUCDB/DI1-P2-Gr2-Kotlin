package com.diiage.edusec.ui.screens.challenges

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.R
import com.diiage.edusec.ui.core.components.ChallengeCard
import com.diiage.edusec.ui.core.components.CustomTitle
import com.diiage.edusec.ui.core.components.Screen
import com.diiage.edusec.ui.core.components.input.DifficultyFilter
import com.diiage.edusec.ui.core.components.input.DifficultyLevel
import com.diiage.edusec.ui.core.components.input.SearchBar
import com.diiage.edusec.ui.core.components.layout.MainScaffold
import java.time.LocalDate

@Composable
fun ChallengesScreen(navController: NavController) {
    Screen(
        viewModel = viewModel<ChallengesViewModel>(),
        navController = navController
    ) { state, viewModel ->
        Content(
            navController = navController,
            state = state,
            handleAction = viewModel::handleAction
        )
    }
}

@Composable
private fun Content(
    navController : NavController,
    state: ChallengesContracts.UiState,
    handleAction: (ChallengesContracts.UiAction) -> Unit
) {
    MainScaffold(navController = navController) { _ ->
        if (state.isLoading) {
            LoadingState()
        } else if (state.error != null) {
            ErrorState(
                error = state.error,
                onRetry = { /* You can add retry logic if needed */ }
            )
        } else {
            ChallengesContent(
                state = state,
                handleAction = handleAction
            )
        }
    }
}

@Composable
private fun ChallengesContent(
    state: ChallengesContracts.UiState,
    handleAction: (ChallengesContracts.UiAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding()
            .fillMaxSize()
    ) {
        CustomTitle("Challenges")

        // Search bar
        SearchBar(
            value = state.searchQuery,
            onValueChange = { handleAction(ChallengesContracts.UiAction.SearchQueryChanged(it)) },
            placeholder = "Rechercher...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Difficulty filter
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            DifficultyFilter(
                selectedDifficulties = state.selectedDifficulties,
                onDifficultySelected = { difficulty ->
                    handleAction(ChallengesContracts.UiAction.DifficultySelected(difficulty))
                },
                modifier = Modifier.wrapContentWidth()
            )
        }

        // List of challenges
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = state.filteredChallenges,
                key = { it.id }
            ) { challenge ->
                ChallengeCard(
                    title = challenge.name,
                    description = challenge.description,
                    date = LocalDate.now().plusDays(challenge.id.hashCode() % 30L),
                    difficulty = when (challenge.difficulty) {
//                        1 -> context.getString(R.string.challenges_card_easy)
                        1 -> stringResource(R.string.challenges_card_easy)
                        2 -> "Normal"
                        else -> "Difficile"
                    },
                    isEquipe = challenge.isGuildChallenge,
                    onClick = {
                        handleAction(ChallengesContracts.UiAction.ChallengeClicked(challenge.id))
                    }
                )
            }

            // Show empty state if no challenges match the filters
            if (state.filteredChallenges.isEmpty() && !state.isLoading) {
                item {
                    EmptyState(
                        searchQuery = state.searchQuery.text,
                        selectedDifficulties = state.selectedDifficulties,
                        onResetFilters = { handleAction(ChallengesContracts.UiAction.ResetFilters) },
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    error: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.TextButton(onClick = onRetry) {
                Text(text = "Réessayer")
            }
        }
    }
}

// Empty state component (updated with reset callback)
@Composable
private fun EmptyState(
    searchQuery: String,
    selectedDifficulties: Set<DifficultyLevel>,
    onResetFilters: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.material3.TextButton(
            onClick = onResetFilters
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