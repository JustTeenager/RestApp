package com.example.restapp.data.manager.api.profile

import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.domain.dto.LoginDTO
import com.example.restapp.domain.dto.TokenDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class ProfileApiManagerImpl @Inject constructor(
    private val client: HttpClient,
) : ProfileApiManager {

    override suspend fun login(login: String, password: String): TokenDTO {
        return client.post {
            url("/auth/jwt/create")
            contentType(ContentType.Application.Json)
            body = LoginDTO(
                username = login,
                password = password
            )
        }
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }
}