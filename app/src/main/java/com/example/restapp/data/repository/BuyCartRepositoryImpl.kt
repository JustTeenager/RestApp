package com.example.restapp.data.repository

import com.example.restapp.data.manager.StorageManager
import com.example.restapp.data.manager_contracts.ApiManager
import com.example.restapp.data.model.Cart
import com.example.restapp.domain.repository.BuyCartRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class BuyCartRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager,
    @Named("Api") apiManager: ApiManager,
    @Named("Mock") mockApiManager: ApiManager,
) : BuyCartRepository {

    private val _productCart = MutableStateFlow<Cart?>(null)

    private var isProductCartLaunched = false

    val productCart = _productCart.asStateFlow()

    private val isMockUsing = true

    val manager = if (isMockUsing) mockApiManager else apiManager

    private suspend fun initProductsCart() {
        coroutineScope {
            launch {
                storageManager.productsInCart.onEach {
                    _productCart.emit(
                        _productCart.value?.copy(
                            productList = it
                        ) ?: Cart(
                            productList = it,
                            totalPrice = storageManager.totalPrice.value,
                            address = ""
                        )
                    )
                }
            }
            launch {
                storageManager.totalPrice.onEach {
                    _productCart.emit(
                        _productCart.value?.copy(
                            totalPrice = it
                        ) ?: Cart(
                            productList = listOf(),
                            totalPrice = it,
                            address = ""
                        )
                    )
                }
            }
        }
    }

    override suspend fun getProductsCart(): StateFlow<Cart?> {
        if (!isProductCartLaunched) {
            initProductsCart()
            isProductCartLaunched = true
        }
        return productCart
    }

    override suspend fun buyCart() {
        _productCart.value?.let {
            manager.buyProductlist(it)
        }
    }
}