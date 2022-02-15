package com.example.restapp.data.manager

import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.Product
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(
    private val client: HttpClient
) : ApiManager {

    override suspend fun loadProducts(): List<Product> {
        client.use {
            return client.get("http://192.168.43.2:8000/api/product/?format=json")
        }
    }
}