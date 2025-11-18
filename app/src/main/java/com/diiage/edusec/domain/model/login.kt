package com.diiage.edusec.domain.model

data class LoginRequest(
    val identifier: String
)

data class LoginResponse(
    val success: Boolean,
    val userId: String? = null,
    val error: String? = null
)