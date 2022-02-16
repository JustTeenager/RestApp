package com.example.restapp.domain.contracts

import com.example.restapp.data.model.Product

interface ApiManager {
    suspend fun loadProducts(): List<Product>
}