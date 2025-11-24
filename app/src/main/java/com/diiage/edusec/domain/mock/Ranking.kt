package com.diiage.edusec.domain.mock

import com.diiage.edusec.domain.model.Rank

fun mockRankingUsers(): List<Rank> = List(10) { index ->
    Rank(
        rank = index + 1,
        name = "User ${index + 1}",
        score = 2000 - index * 100
    )
}

fun mockRankingTeams(): List<Rank> = List(10) { index ->
    Rank(
        rank = index + 1,
        name = "Team ${index + 1}",
        score = 2000 - index * 100
    )
}
