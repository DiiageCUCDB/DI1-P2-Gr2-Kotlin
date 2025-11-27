package com.diiage.edusec.ui.screens.ranking

import android.app.Application
import com.diiage.edusec.domain.mock.mockRankingTeams
import com.diiage.edusec.domain.mock.mockRankingUsers
import com.diiage.edusec.domain.model.Rank
import com.diiage.edusec.ui.core.ViewModel

interface RankingContracts {
    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        var displayedList: List<Rank> = mockRankingUsers(),
        //val teams: List<Rank> = mockRankingTeams(),
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
    //private val rankingService: RankingService by inject()

    fun handleAction(action: RankingContracts.UiAction){
        when (action) {
            is RankingContracts.UiAction.IsPlayerListStillDisplayed -> updateIsPlayerListStillDisplayed(action.selection)
        }
    }

    private fun updateIsPlayerListStillDisplayed(selection: Boolean) {
        updateState {
            copy(
                isPlayerListDisplayed = selection,
                displayedList = if (selection) mockRankingUsers() else mockRankingTeams()
            )
        }

    }
}