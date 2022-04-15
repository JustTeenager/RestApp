package com.example.restapp.data.manager_contracts

import com.example.restapp.domain.dto.TokenDTO

interface ProfileApiManager {
    suspend fun login(login: String, password: String): TokenDTO

    suspend fun register()
}