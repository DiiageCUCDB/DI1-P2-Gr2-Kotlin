package com.diiage.edusec.model

data class LoginRequest(
    val identifier: String
)

data class LoginResponse(
    val success: Boolean,
    val userId: String? = null,
    val error: String? = null
)