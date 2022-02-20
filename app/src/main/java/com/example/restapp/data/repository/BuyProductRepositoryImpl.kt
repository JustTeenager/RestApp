package com.example.restapp.data.repository

import com.example.restapp.data.manager.StorageManager
import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.BuyProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class BuyProductRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager
) : BuyProductRepository {

    override fun addProductToCart(product: Product) {
        storageManager.addProductToCart(product)
    }

    override fun removeProductFromCart(product: Product) {
        storageManager.removeProductFromCart(product)
    }

    override fun getProductsCount(): StateFlow<Int> {
        return storageManager.productsCount.asStateFlow()
    }

    override fun getProductsToBuyFlow(): Flow<List<Pair<Int, Product>>> {
        return storageManager.productsInCart
    }
}