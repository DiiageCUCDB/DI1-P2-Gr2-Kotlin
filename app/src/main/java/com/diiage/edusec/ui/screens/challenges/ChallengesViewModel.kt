package com.diiage.edusec.ui.screens.challenges

import android.app.Application
import androidx.compose.ui.text.input.TextFieldValue
import com.diiage.edusec.domain.model.Challenge
import com.diiage.edusec.ui.core.ViewModel
import com.diiage.edusec.ui.core.components.input.DifficultyLevel
import java.util.Locale
import com.diiage.edusec.domain.repository.ChallengeRepository
import org.koin.core.component.inject

interface ChallengesContracts {
    data class UiState(
        val challenges: List<Challenge> = emptyList(),
        val filteredChallenges: List<Challenge> = emptyList(),
        val searchQuery: TextFieldValue = TextFieldValue(""),
        val selectedDifficulties: Set<DifficultyLevel> = DifficultyLevel.entries.toSet(),
        val isLoading: Boolean = true,
        val error: String? = null
    )

    sealed interface UiAction {
        data class SearchQueryChanged(val query: TextFieldValue) : UiAction
        data class DifficultySelected(val difficulty: DifficultyLevel) : UiAction
        data class ChallengeClicked(val challengeId: String) : UiAction
        object ResetFilters : UiAction
    }

    sealed interface Event {
        data class NavigateToQuiz(val challengeId: String) : Event
    }
}

class ChallengesViewModel(
    application: Application
) : ViewModel<ChallengesContracts.UiState>(
    initialState = ChallengesContracts.UiState(),
    application = application
) {

    private val challengeRepository: ChallengeRepository by inject()

    init {
        loadChallenges()
    }

    fun handleAction(action: ChallengesContracts.UiAction) {
        when (action) {
            is ChallengesContracts.UiAction.SearchQueryChanged -> updateSearchQuery(action.query)
            is ChallengesContracts.UiAction.DifficultySelected -> toggleDifficulty(action.difficulty)
            is ChallengesContracts.UiAction.ChallengeClicked -> navigateToQuiz(action.challengeId)
            is ChallengesContracts.UiAction.ResetFilters -> resetFilters()
        }
    }

    private fun loadChallenges() {
        collectData(
            source = { challengeRepository.getAllChallenges() }
        ) {
            onSuccess { challenges ->
                updateState {
                    copy(
                        challenges = challenges,
                        filteredChallenges = filterChallenges(
                            challenges = challenges,
                            searchQuery = this.searchQuery, // Use 'this' to access current state
                            selectedDifficulties = this.selectedDifficulties
                        ),
                        isLoading = false,
                        error = null
                    )
                }
            }
            onFailure { error ->
                updateState {
                    copy(
                        challenges = emptyList(),
                        filteredChallenges = emptyList(),
                        isLoading = false,
                        error = error.message ?: "Erreur lors du chargement des défis"
                    )
                }
            }
        }
    }

    private fun updateSearchQuery(query: TextFieldValue) {
        updateState {
            val filteredChallenges = filterChallenges(
                challenges = this.challenges,
                searchQuery = query,
                selectedDifficulties = this.selectedDifficulties
            )

            copy(
                searchQuery = query,
                filteredChallenges = filteredChallenges
            )
        }
    }

    private fun toggleDifficulty(difficulty: DifficultyLevel) {
        updateState {
            val newSelectedDifficulties = if (this.selectedDifficulties.contains(difficulty)) {
                this.selectedDifficulties - difficulty
            } else {
                this.selectedDifficulties + difficulty
            }

            val filteredChallenges = filterChallenges(
                challenges = this.challenges,
                searchQuery = this.searchQuery,
                selectedDifficulties = newSelectedDifficulties
            )

            copy(
                selectedDifficulties = newSelectedDifficulties,
                filteredChallenges = filteredChallenges
            )
        }
    }

    private fun resetFilters() {
        updateState {
            val filteredChallenges = filterChallenges(
                challenges = this.challenges,
                searchQuery = TextFieldValue(""),
                selectedDifficulties = DifficultyLevel.entries.toSet()
            )

            copy(
                searchQuery = TextFieldValue(""),
                selectedDifficulties = DifficultyLevel.entries.toSet(),
                filteredChallenges = filteredChallenges
            )
        }
    }

    private fun navigateToQuiz(challengeId: String) {
        sendEvent(ChallengesContracts.Event.NavigateToQuiz(challengeId))
    }

    private fun filterChallenges(
        challenges: List<Challenge>,
        searchQuery: TextFieldValue,
        selectedDifficulties: Set<DifficultyLevel>
    ): List<Challenge> {
        // First filter by search
        val searchFiltered = if (searchQuery.text.isBlank()) {
            challenges
        } else {
            challenges.filter { challenge ->
                challenge.name.lowercase(Locale.getDefault())
                    .contains(searchQuery.text.lowercase(Locale.getDefault())) ||
                        challenge.description.lowercase(Locale.getDefault())
                            .contains(searchQuery.text.lowercase(Locale.getDefault()))
            }
        }

        // Then filter by difficulties (show all if no difficulty selected)
        return if (selectedDifficulties.isEmpty()) {
            searchFiltered
        } else {
            searchFiltered.filter { challenge ->
                val challengeDifficultyLevel = when (challenge.difficulty) {
                    1 -> DifficultyLevel.EASY
                    2 -> DifficultyLevel.MEDIUM
                    else -> DifficultyLevel.HARD
                }
                selectedDifficulties.contains(challengeDifficultyLevel)
            }
        }
    }
}