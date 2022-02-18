package com.example.restapp.data.manager

import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.model.Product
import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toProductList
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(
    private val client: HttpClient,
    private val productMapper: FromDtoToProductMapper
) : ApiManager {

    override suspend fun loadProducts(): List<Product> {
        return client.get<List<ProductDTO>>("/product/?format=json")
            .toProductList(productMapper)
    }
}