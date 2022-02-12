package com.example.restapp.domain.repository

import com.example.restapp.domain.dto.Product

interface LoadProductsRepository {
    fun loadProducts(): List<Product>
}