package com.diiage.edusec.domain.repository

import com.diiage.edusec.domain.model.UserDetails

interface UserSessionManager {
    fun saveUser(user: UserDetails)
    fun getUser(): UserDetails?
    fun clearUser()
    fun isLoggedIn(): Boolean
}