package com.diiage.edusec.data.repository

import com.diiage.edusec.data.api.LoginApiService
import com.diiage.edusec.model.LoginRequest
import com.diiage.edusec.model.LoginResponse

class LoginRepository(private val apiService: LoginApiService) {
    suspend fun login(identifier: String): LoginResponse {
        return apiService.login(LoginRequest(identifier))
    }

    fun validateIdentifier(identifier: String): Boolean {
        return identifier.isNotBlank() &&
                identifier.length >= 3 &&
                identifier.matches(Regex("^[a-zA-Z0-9_]+$"))
    }
}