package com.example.restapp.domain.repository

import com.example.restapp.data.model.Cart
import kotlinx.coroutines.flow.Flow

interface BuyCartRepository {

    fun getProductsCart(): Flow<Cart?>

    fun getProductsCount(): Flow<Int>

    //TODO В отдельное репо покупка и адрес

    fun setCartAddress(address: String)

    suspend fun buyCart(cart: Cart)
}