package com.diiage.edusec.domain.repository

import com.diiage.edusec.domain.model.LoginResponse

interface LoginRepository {
    /**
     * Authenticates a user with the provided identification.
     *
     * @param identification The user's identification (username, email, etc.)
     * @return LoginResponse containing authentication result
     */
    suspend fun login(identification: String): LoginResponse
}