package com.diiage.edusec.domain.model

data class UserDetails(
    val id: String,
    val username: String,
    val score: Int,
    val guildId: String?
)