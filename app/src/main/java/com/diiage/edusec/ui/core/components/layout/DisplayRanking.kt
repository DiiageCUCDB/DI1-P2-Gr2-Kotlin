package com.diiage.edusec.ui.core.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.domain.mock.mockRankingTeams
import com.diiage.edusec.domain.mock.mockRankingUsers
import com.diiage.edusec.ui.core.components.LoadingPage
import com.diiage.edusec.ui.core.components.ScoreRow

@Composable
fun DisplayRanking(isUsers: Boolean = false){
    val mockUsers = mockRankingUsers
    val mockTeams = mockRankingTeams
    //TODO: Mettre le isLoading a true et gérer les coroutines pour l'appel API
    val isLoading = false


    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LoadingPage(
                modifier = Modifier.size(64.dp)
            )
        }
    }
    else{
        if (!isUsers) {
            mockTeams.forEach { team ->
                ScoreRow(
                    rank = team.rank,
                    name = team.name,
                    score = team.score
                )
            }
        }else{
            mockUsers.forEach { user ->
                ScoreRow(
                    rank = user.rank,
                    name = user.name,
                    score = user.score
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayRankingPreview(){
    DisplayRanking()
}