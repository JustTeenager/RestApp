package com.example.restapp.data.repository

import com.example.restapp.data.manager.StorageManager
import com.example.restapp.data.manager_contracts.ApiManager
import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.BuyProductRepository
import javax.inject.Inject
import javax.inject.Named


class BuyProductRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager,
) : BuyProductRepository {

    private val isMockUsing = true

    override fun addProductToCart(product: Product) {
        storageManager.addProductToCart(product)
    }

    override fun removeProductFromCart(product: Product) {
        storageManager.removeProductFromCart(product)
    }

    override fun getProductCount(product: Product): Int {
        return storageManager.getProductCount(product)
    }

    /* suspend fun buyProductlist() {
         return apiManager.buyProductlist(storageManager.getCartDTO())
     }*/
}