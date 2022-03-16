package com.example.restapp.data.manager_contracts

interface DataStoreManager {

    companion object {
        const val AUTHORIZATION_PREF_KEY = "authorization_pref_key"
    }

    suspend fun addProfileToken(token: String)

    suspend fun getProfileToken(): String?
}