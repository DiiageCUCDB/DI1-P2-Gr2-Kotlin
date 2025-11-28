package com.diiage.edusec.ui.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.domain.mock.mockRankingTeams
import com.diiage.edusec.domain.mock.mockRankingUsers
import com.diiage.edusec.domain.model.Rank
import kotlin.collections.forEach


@Composable
fun DisplayRanking(
    listRank: List<Rank>,
    modifier: Modifier = Modifier
) {
    val isLoading = false

    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LoadingPage(
                modifier = Modifier.Companion.size(64.dp)
            )
        }
    } else {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            listRank.forEach { entity ->
                ScoreRow(
                    rank = entity.rank,
                    name = entity.name,
                    score = entity.score,
                )
                Spacer(modifier = Modifier.size(7.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayRankingUsersPreview() {
    val users = mockRankingUsers()
    val teams = mockRankingTeams()

//    DisplayRanking(
//        listRank = users,
//        modifier = Modifier.Companion
//            .fillMaxSize()
//            .padding(16.dp)
//    )
}