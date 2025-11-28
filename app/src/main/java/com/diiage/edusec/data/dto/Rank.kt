package com.diiage.edusec.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Rank(
    val rank: Int,
    val name: String,
    val score: Int
)