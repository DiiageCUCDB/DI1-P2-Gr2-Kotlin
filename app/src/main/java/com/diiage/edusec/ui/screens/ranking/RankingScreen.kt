package com.diiage.edusec.ui.screens.ranking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.domain.mock.mockRankingTeams
import com.diiage.edusec.domain.mock.mockRankingUsers
import com.diiage.edusec.ui.core.components.CustomTitle
import com.diiage.edusec.ui.core.components.input.ToggleElementButton
import com.diiage.edusec.ui.core.components.layout.CenteredColumn
import com.diiage.edusec.ui.core.components.DisplayRanking
import com.diiage.edusec.ui.core.components.layout.MainScaffold

@Composable
fun RankingScreen(navController: NavController) {
    var value by remember { mutableStateOf("Utilisateur") }
    val users = mockRankingUsers()
    val teams = mockRankingTeams()


    MainScaffold(navController = navController) {
        CenteredColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            CustomTitle("Ranking")
            ToggleElementButton("Utilisateur", "Équipe", value, { value = it })

            Spacer(modifier = Modifier.size(15.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                    DisplayRanking(if (value == "Utilisateur") true else false, users, teams)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    RankingScreen(navController = rememberNavController())
}