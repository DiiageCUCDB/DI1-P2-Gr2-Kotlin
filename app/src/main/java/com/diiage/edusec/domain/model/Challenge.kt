package com.diiage.edusec.domain.model

data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean
)