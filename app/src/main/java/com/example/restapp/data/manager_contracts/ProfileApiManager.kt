package com.example.restapp.data.manager_contracts

interface ProfileApiManager {
    suspend fun login(login: String, password: String): String

    suspend fun register()
}