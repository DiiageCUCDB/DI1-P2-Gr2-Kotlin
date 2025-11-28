package com.diiage.edusec.ui.screens.ranking

import android.app.Application
import com.diiage.edusec.domain.model.Rank
import com.diiage.edusec.domain.repository.RankingRepository
import com.diiage.edusec.ui.core.ViewModel
import org.koin.core.component.inject

interface RankingContracts {
    data class UiState(
        var isLoading: Boolean = false,
        var errorMessage: String? = null,
        val userRanking: List<Rank> = emptyList(),
        val teamsRanking: List<Rank> = emptyList(),
        var displayedList: List<Rank> = emptyList(),
        var isPlayerListDisplayed: Boolean = true
    )

    sealed interface UiAction {
        data class IsPlayerListStillDisplayed(val selection: Boolean) : UiAction
    }
}

class RankingViewModel(
    application: Application
) : ViewModel<RankingContracts.UiState>(
    initialState = RankingContracts.UiState(),
    application = application
){
    private val rankingRepository: RankingRepository by inject()

    init {
        loadUserRanking()
        loadTeamsRanking()
    }
    fun handleAction(action: RankingContracts.UiAction){
        when (action) {
            is RankingContracts.UiAction.IsPlayerListStillDisplayed -> updateIsPlayerListStillDisplayed(action.selection)
        }
    }

    private fun loadUserRanking(){
        collectData(
            source = {rankingRepository.getUsersRanking()},
        ){
            onSuccess { userRanking ->
                updateState {
                    copy(
                        userRanking = userRanking,
                        displayedList = userRanking,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
            onFailure { error ->
                updateState {
                    copy(
                        userRanking = emptyList(),
                        isLoading = false,
                        errorMessage = error.message ?: "Erreur lors du chargement du classement"
                    )
                }

            }
        }
    }

    private fun loadTeamsRanking(){
        collectData(
            source = {rankingRepository.getTeamsRanking()},
        ){
            onSuccess { teamsRanking ->
                updateState {
                    copy(
                        teamsRanking = teamsRanking,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
            onFailure { error ->
                updateState {
                    copy(
                        teamsRanking = emptyList(),
                        isLoading = false,
                        errorMessage = error.message ?: "Erreur lors du chargement du classement"
                    )
                }

            }
        }
    }
    private fun updateIsPlayerListStillDisplayed(selection: Boolean) {
        updateState {
            copy(
                isPlayerListDisplayed = selection,
                displayedList = if (selection) userRanking else teamsRanking
            )
        }

    }
}