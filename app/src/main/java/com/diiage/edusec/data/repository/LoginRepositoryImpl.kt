package com.diiage.edusec.data.repository

import com.diiage.edusec.data.remote.LoginAPI
import com.diiage.edusec.domain.model.UserDetails
import com.diiage.edusec.domain.repository.LoginRepository

internal class LoginRepositoryImpl(
    private val loginAPI: LoginAPI
) : LoginRepository {

    override suspend fun login(identification: String): UserDetails {
        val apiResponse = loginAPI.createLogin(identification)

        if (!apiResponse.success) {
            throw Exception(apiResponse.message)
        }

        return UserDetails(
            id = apiResponse.result.id,
            username = apiResponse.result.username,
            score = apiResponse.result.score,
            guildId = apiResponse.result.guildId
        )
    }
}