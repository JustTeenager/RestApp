package com.example.restapp.data.repository

import android.util.Log
import com.example.restapp.BuildConfig
import com.example.restapp.data.manager_contracts.ProductApiManager
import com.example.restapp.data.manager_contracts.StorageManager
import com.example.restapp.data.model.Cart
import com.example.restapp.domain.repository.BuyCartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Named

class BuyCartRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager,
    @Named("Api") productApiManager: ProductApiManager,
    @Named("Mock") mockProductApiManager: ProductApiManager,
) : BuyCartRepository {

    private val manager =
        if (BuildConfig.IS_MOCK_USING) mockProductApiManager else productApiManager

    override fun getProductsCart(): Flow<Cart?> {
        Log.d("tut_BuyCartRepo", "gettingProductsCart")
        return storageManager.cartAddress
            .combine(storageManager.productsInCart) { address, productInfo ->
                productInfo to address
            }
            .zip(storageManager.totalPrice) { productInfoWithAddress, price ->
                Cart(
                    productList = productInfoWithAddress.first,
                    totalPrice = price,
                    address = productInfoWithAddress.second
                )
            }
    }

    override fun getProductsCount(): Flow<Int> = storageManager.productsTotalCount

    override fun getCartAddress(): String = storageManager.cartAddress.value

    override fun setCartAddress(address: String) {
        storageManager.updateAddress(address)
    }

    override suspend fun buyCart(cart: Cart) {
        manager.buyProductCart(cart)
    }
}