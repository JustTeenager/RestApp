package com.example.restapp.data.manager_contracts

import com.example.restapp.data.model.Cart
import com.example.restapp.data.model.Product

interface ProductApiManager {
    suspend fun loadProducts(): List<Product>

    suspend fun buyProductCart(cart: Cart)
}