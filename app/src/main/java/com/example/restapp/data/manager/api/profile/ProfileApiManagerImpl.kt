package com.example.restapp.data.manager.api.profile

import com.example.restapp.data.manager.DataStoreManager
import com.example.restapp.data.manager_contracts.ProfileApiManager
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class ProfileApiManagerImpl @Inject constructor(
    private val client: HttpClient,
    private val dataStoreManager: DataStoreManager
) : ProfileApiManager {

    override suspend fun login(login: String, password: String): String {
        return client.get {
            url("")
            contentType(ContentType.Application.Json)
            header(
                HttpHeaders.Authorization,
                dataStoreManager.getProfileToken()
            )
            body = Parameters.build {
                append("login", login)
                append("password", password)
            }
        }
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }
}