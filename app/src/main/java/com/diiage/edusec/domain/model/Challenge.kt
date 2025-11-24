package com.diiage.edusec.domain.model

data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean
)

data class ChallengesResult(
    val challenges: List<Challenge>,
    val totalChallenges: Int,
    val totalPages: Int,
    val currentPage: Int
)