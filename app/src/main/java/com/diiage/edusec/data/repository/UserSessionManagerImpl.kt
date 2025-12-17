package com.diiage.edusec.data.repository

import com.diiage.edusec.domain.model.UserDetails
import com.diiage.edusec.domain.repository.UserSessionManager

internal class UserSessionManagerImpl : UserSessionManager {
    private var currentUser: UserDetails? = null

    override fun saveUser(user: UserDetails) {
        currentUser = user
    }

    override fun getUser(): UserDetails? = currentUser

    override fun clearUser() {
        currentUser = null
    }

    override fun isLoggedIn(): Boolean = currentUser != null
}