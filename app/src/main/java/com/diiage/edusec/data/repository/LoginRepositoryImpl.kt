package com.diiage.edusec.data.repository

import com.diiage.edusec.domain.model.LoginResponse
import com.diiage.edusec.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(identification: String): LoginResponse {
        // Simulate API call delay
        delay(1000)

        // Mock authentication logic
        return when {
            identification.equals("admin", ignoreCase = true) -> {
                LoginResponse(
                    success = true,
                    userId = "admin-123"
                )
            }
            identification.equals("user", ignoreCase = true) -> {
                LoginResponse(
                    success = true,
                    userId = "user-456"
                )
            }
            identification.equals("error", ignoreCase = true) -> {
                LoginResponse(
                    success = false,
                    error = "Invalid credentials"
                )
            }
            else -> {
                // For any other identifier, simulate successful login
                LoginResponse(
                    success = true,
                    userId = "user-${identification.hashCode().absoluteValue}"
                )
            }
        }
    }
}