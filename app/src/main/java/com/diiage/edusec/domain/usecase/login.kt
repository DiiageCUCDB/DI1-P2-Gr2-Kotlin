package com.diiage.edusec.domain.usecase

import com.diiage.edusec.data.remote.LoginRepository
import com.diiage.edusec.domain.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService(
    private val loginRepository: LoginRepository = LoginRepository()
) {

    fun validateIdentifier(identification: String): Boolean {
        return identification.isNotBlank() &&
                identification.length >= 3 &&
                identification.matches(Regex("^[a-zA-Z0-9_]+$"))
    }

    suspend fun login(identification: String): Result<LoginResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response = loginRepository.login(identification)
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