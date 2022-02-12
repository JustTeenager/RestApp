package com.example.restapp.data.repository

import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.domain.dto.Product
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class LoadProductsRepositoryImpl @Inject constructor(
    val client: HttpClient
): LoadProductsRepository {

    val isMockUsing = true

    override fun loadProducts(): List<Product> {
        loadProducts()
        return emptyList()
    }
}