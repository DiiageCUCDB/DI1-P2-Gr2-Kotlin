package com.diiage.edusec.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class ChallengesResult(
    val challenges: List<Challenge>,
    val totalChallenges: Int,
    val totalPages: Int,
    val currentPage: Int
)