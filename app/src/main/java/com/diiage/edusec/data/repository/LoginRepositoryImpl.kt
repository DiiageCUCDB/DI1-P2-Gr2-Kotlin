package com.diiage.edusec.data.repository

import com.diiage.edusec.domain.model.LoginResponse

interface LoginRepositoryImpl {
    suspend fun login(identification : String): LoginResponse
}