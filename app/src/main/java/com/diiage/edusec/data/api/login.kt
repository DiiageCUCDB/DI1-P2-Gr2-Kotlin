package com.diiage.edusec.data.api

import com.diiage.edusec.domain.model.LoginRequest
import com.diiage.edusec.domain.model.LoginResponse

interface LoginApiService {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}