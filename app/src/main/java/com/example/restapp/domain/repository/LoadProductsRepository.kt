package com.example.restapp.domain.repository

import com.example.restapp.data.model.Product

interface LoadProductsRepository {
    suspend fun loadProducts(): Result<List<Product>>
}