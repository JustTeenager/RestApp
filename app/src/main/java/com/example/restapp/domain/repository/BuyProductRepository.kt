package com.example.restapp.domain.repository

import com.example.restapp.data.model.Product

interface BuyProductRepository {
    fun addProductToCart(product: Product)

    fun removeProductFromCart(product: Product)
}