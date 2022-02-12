package com.example.restapp.domain

import com.example.restapp.dto.Product

interface LoadProductsRepository {
    fun loadProducts(): List<Product>
}