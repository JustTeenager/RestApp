package com.example.restapp.data.manager

import com.example.restapp.data.model.Product
import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toProductList
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(
    private val client: HttpClient
) : ApiManager {

    override suspend fun loadProducts(): List<Product> {
        client.use {
            return client.get<List<ProductDTO>>("/product/?format=json").toProductList()
        }
    }
}