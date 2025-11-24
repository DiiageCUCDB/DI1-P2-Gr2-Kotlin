package com.diiage.edusec.data.dto

data class ApiResponse<T>(
    val results: List<T>,
    val generationTimeMs: Double,
    val success: Boolean,
    val message: String
)