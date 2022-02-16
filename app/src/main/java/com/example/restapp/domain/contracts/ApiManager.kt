package com.example.restapp.domain.contracts

import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO

interface ApiManager {
    suspend fun loadProducts(): List<Product>
}