package com.example.restapp.data.manager

import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.Product
import io.ktor.client.*
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(
    private val client: HttpClient
) : ApiManager {
    override suspend fun loadProducts(): List<Product> {
        /*client.use {
            return client.get<"">()
        }*/
        TODO("Добавить url")
    }
}