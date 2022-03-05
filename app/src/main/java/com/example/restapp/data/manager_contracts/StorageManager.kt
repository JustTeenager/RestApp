package com.example.restapp.data.manager_contracts

import com.example.restapp.data.model.Product

interface StorageManager {

    fun addProductToCart(product: Product)

    fun removeProductFromCart(product: Product)

    fun getProductCount(product: Product): Int

    fun updateAddress(address: String)
}