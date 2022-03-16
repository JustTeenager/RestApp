package com.example.restapp.data.manager_contracts

import com.example.restapp.data.model.Product
import kotlinx.coroutines.flow.StateFlow

interface StorageManager {

    val productsInCart: StateFlow<List<Pair<Int, Product>>>

    val productsTotalCount: StateFlow<Int>
    val totalPrice: StateFlow<Int>

    val cartAddress: StateFlow<String>

    fun addProductToCart(product: Product)

    fun removeProductFromCart(product: Product)

    fun getProductCount(product: Product): Int

    fun updateAddress(address: String)
}