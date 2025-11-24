package com.diiage.edusec.domain.mock

import com.diiage.edusec.domain.model.Guild

val mockGuilds: List<Guild> = List(25) { index ->
    Guild(
        id = "${index + 1}",
        name = "Groupe ${index + 1}",
        score = index + 100,
        members = index + 1
    )
}