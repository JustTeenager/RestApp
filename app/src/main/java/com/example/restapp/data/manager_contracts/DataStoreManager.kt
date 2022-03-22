package com.example.restapp.data.manager_contracts

interface DataStoreManager {

    companion object {
        const val TOKEN_PREF_KEY = "authorization_pref_key"
        const val LOGIN_PREF_KEY = "authorization_pref_key"
        const val PASSWORD_PREF_KEY = "authorization_pref_key"
    }

    suspend fun addProfileToken(token: String)

    suspend fun getProfileToken(): String?

    suspend fun addProfileLogin(login: String)

    suspend fun getProfileLogin(): String?

    suspend fun addProfilePassword(password: String)

    suspend fun getProfilePassword(): String?
}