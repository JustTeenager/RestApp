package com.example.restapp.domain.repository

import com.example.restapp.data.model.Cart
import kotlinx.coroutines.flow.StateFlow

interface BuyCartRepository {

    suspend fun getProductsCart(): StateFlow<Cart?>

    suspend fun buyCart()
}