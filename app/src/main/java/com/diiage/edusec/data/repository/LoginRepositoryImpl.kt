package com.diiage.edusec.data.repository

import com.diiage.edusec.data.remote.LoginAPI
import com.diiage.edusec.domain.model.UserDetails
import com.diiage.edusec.domain.repository.LoginRepository
import com.diiage.edusec.domain.repository.UserSessionManager

internal class LoginRepositoryImpl(
    private val loginAPI: LoginAPI,
    private val userSessionManager: UserSessionManager
) : LoginRepository {

    override suspend fun login(identification: String): UserDetails {
        val apiResponse = loginAPI.createLogin(identification)

        if (!apiResponse.success) {
            throw Exception(apiResponse.message)
        }

        val userDetails = UserDetails(
            id = apiResponse.result.id,
            username = apiResponse.result.username,
            score = apiResponse.result.score,
            guildId = apiResponse.result.guildId
        )

        // Save user to session manager
        userSessionManager.saveUser(userDetails)

        return userDetails
    }
}