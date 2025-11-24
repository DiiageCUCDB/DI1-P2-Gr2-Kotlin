package com.diiage.edusec.domain.mock

import com.diiage.edusec.domain.model.Rank

val mockRankingUsers: List<Rank> = List(10) { index ->
    Rank(
        rank = index + 1,
        name = "Pseudo ${index + 1}",
        score = 2000 - index*100
    )
}

val mockRankingTeams: List<Rank> = List(10) { index ->
    Rank(
        rank = index + 1,
        name = "Équipe ${index + 1}",
        score = 2000 - index*100
    )
}