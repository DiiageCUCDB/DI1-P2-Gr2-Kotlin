package com.diiage.edusec.ui.screens.ranking

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.domain.mock.mockRankingTeams
import com.diiage.edusec.domain.mock.mockRankingUsers
import com.diiage.edusec.ui.core.components.CustomTitle
import com.diiage.edusec.ui.core.components.input.ToggleElementButton
import com.diiage.edusec.ui.core.components.layout.CenteredColumn
import com.diiage.edusec.ui.core.components.DisplayRanking
import com.diiage.edusec.ui.core.components.Screen
import com.diiage.edusec.ui.core.components.layout.MainScaffold

@Composable
fun RankingScreen(navController: NavController) {
    Screen(
        viewModel = viewModel<RankingViewModel>(),
        navController = navController
    ){
        state, viewModel ->
        Content(
            navController = navController,
            state = state,
            handleAction = viewModel::handleAction
        )
    }
}
@Composable
private fun Content(
    navController: NavController,
    state: RankingContracts.UiState = RankingContracts.UiState(),
    handleAction: (RankingContracts.UiAction) -> Unit = {}
){
    MainScaffold(navController = navController) {
    CenteredColumn(
        modifier = Modifier
            .padding(horizontal = 11.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CustomTitle("Classement")
        Spacer(modifier = Modifier.size(20.dp))
        ToggleElementButton("Utilisateur", "Équipe", state.isPlayerListDisplayed, { handleAction(RankingContracts.UiAction.IsPlayerListStillDisplayed(it)) })

        Spacer(modifier = Modifier.size(15.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )   {
                DisplayRanking(state.displayedList)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    Content(
        navController = rememberNavController(),
        state = RankingContracts.UiState(
            isLoading = false,
            errorMessage = null,
            //displayedList = mockRankingUsers(),
            isPlayerListDisplayed = true
        ),
        handleAction = {}
    )
}