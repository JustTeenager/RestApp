package com.example.restapp.domain.repository

import com.example.restapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BuyProductRepository {
    fun addProductToCart(product: Product)

    fun removeProductFromCart(product: Product)

    fun getProductsCount(): StateFlow<Int>

    fun getProductsToBuyFlow(): Flow<List<Pair<Int, Product>>>
}