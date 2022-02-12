package com.example.restapp.domain.contracts

import com.example.restapp.domain.dto.Product

interface ApiManager {
    suspend fun loadProducts(): List<Product>
}