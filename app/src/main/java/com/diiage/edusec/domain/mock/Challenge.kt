package com.diiage.edusec.domain.mock

import com.diiage.edusec.domain.model.Challenge

val mockChallenges: List<Challenge> = List(25) { index ->
    Challenge(
        id = "mock-${index + 1}",
        name = "Challenge ${index + 1}",
        description = "This is a description for Challenge ${index + 1}. Solve interesting problems.",
        difficulty = (1..5).random(),
        isGuildChallenge = (0..1).random() == 1
    )
}