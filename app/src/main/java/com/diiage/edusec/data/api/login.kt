package com.diiage.edusec.data.api

import com.diiage.edusec.model.LoginRequest
import com.diiage.edusec.model.LoginResponse

interface LoginApiService {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}