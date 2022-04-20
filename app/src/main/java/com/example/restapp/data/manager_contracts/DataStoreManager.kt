package com.example.restapp.data.manager_contracts

interface DataStoreManager {

    companion object {
        const val TOKEN_PREF_KEY = "authorization_pref_key"
    }

    suspend fun addProfileToken(token: String)

    suspend fun getProfileToken(): String?

    suspend fun addRefreshToken(token: String)

    suspend fun getRefreshToken(): String?
}