package com.diiage.edusec.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResponse<T>(
    val result: T,
    val generationTime_ms: Double,
    val success: Boolean,
    val message: String
)

