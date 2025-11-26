package com.diiage.edusec.domain.repository

import com.diiage.edusec.domain.model.UserDetails

interface LoginRepository {
    /**
     * Authenticates a user with the provided identification.
     *
     * @param identification The user's identification (username, email, etc.)
     * @return UserDetails containing user information
     * @throws Exception if authentication fails
     */
    suspend fun login(identification: String): UserDetails
}