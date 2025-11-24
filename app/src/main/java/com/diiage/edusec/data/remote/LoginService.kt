package com.diiage.edusec.data.remote

import com.diiage.edusec.data.repository.LoginRepositoryImpl
import com.diiage.edusec.domain.model.LoginResponse

class LoginRepository() : LoginRepositoryImpl {
    override suspend fun login(identification: String): LoginResponse {
        // Simulate API call delay
        kotlinx.coroutines.delay(1000)
        return if (identification == "error") {
            LoginResponse(success = false, error = "Identifiant invalide")
        } else {
            LoginResponse(success = true, userId = "user_$identification")
        }
    }
}