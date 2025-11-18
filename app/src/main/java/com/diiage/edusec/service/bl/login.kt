package com.diiage.edusec.service.bl

import com.diiage.edusec.data.repository.LoginRepository
import com.diiage.edusec.domain.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService(private val loginRepository: LoginRepository) {

    fun validateIdentifier(identifier: String): Boolean {
        return loginRepository.validateIdentifier(identifier)
    }

    suspend fun login(identifier: String): Result<LoginResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response = loginRepository.login(identifier)
                if (response.success) {
                    Result.success(response)
                } else {
                    Result.failure(Exception(response.error ?: "Login failed"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getErrorMessage(identifier: String): String? {
        return when {
            identifier.isBlank() -> "L'identifiant ne peut pas être vide"
            identifier.length < 3 -> "L'identifiant doit contenir au moins 3 caractères"
            !identifier.matches(Regex("^[a-zA-Z0-9_]+$")) -> "L'identifiant ne peut contenir que des lettres, chiffres et underscores"
            else -> null
        }
    }
}