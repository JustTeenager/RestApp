package com.example.restapp.domain.repository

import com.example.restapp.data.model.Cart
import kotlinx.coroutines.flow.Flow

interface BuyCartRepository {

    fun getProductsCart(): Flow<Cart?>

    fun getProductsCount(): Flow<Int>

    //TODO Мы не должны запрашивать это отдельно, косяк
    fun getCartAddress(): String

    fun setCartAddress(address: String)

    suspend fun buyCart(cart: Cart)
}