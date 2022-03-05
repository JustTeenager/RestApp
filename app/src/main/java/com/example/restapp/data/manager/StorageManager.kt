package com.example.restapp.data.manager

import android.util.Log
import com.example.restapp.data.manager_contracts.StorageManager
import com.example.restapp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageManager @Inject constructor(): StorageManager {

    private val _productsInCart =
        MutableStateFlow(listOf<Pair<Int, Product>>())

    val productsInCart
        get() = _productsInCart.asStateFlow()

    val productsTotalCount = MutableStateFlow(0)
    val totalPrice = MutableStateFlow(0)

    val cartAddress = MutableStateFlow("")

    override fun addProductToCart(product: Product) {
        val pairNeeded = _productsInCart.value.find { it.second == product }
        if (pairNeeded != null) {
            val pairNeededIndex = _productsInCart.value.indexOf(pairNeeded)
            val productCount = pairNeeded.first
            updateProductsInCart { list ->
                list[pairNeededIndex] = pairNeeded.copy(first = productCount + 1)
            }
        } else {
            updateProductsInCart { list ->
                list.add(1 to product)
            }
        }
        Log.d("tut_storage_manager", "current cart value is ${productsInCart.value}")
    }

    override fun removeProductFromCart(product: Product) {
        val pairNeeded = _productsInCart.value.find { it.second == product }
        if (pairNeeded != null) {
            val pairNeededIndex = _productsInCart.value.indexOf(pairNeeded)
            val productCount = pairNeeded.first
            updateProductsInCart { list ->
                if (productCount > 1) {
                    list[pairNeededIndex] = pairNeeded.copy(first = productCount - 1)
                } else {
                    list.remove(pairNeeded)
                }
            }
        }
    }

    override fun getProductCount(product: Product): Int {
        return _productsInCart.value
            .find { it.second == product }?.first ?: 0
    }

    override fun updateAddress(address: String) {
        cartAddress.update { address }
    }

    private fun updateProductsInCart(
        applyChanges: (MutableList<Pair<Int, Product>>) -> Unit
    ) {
        _productsInCart.updateAndGet {
            mutableListOf<Pair<Int, Product>>().apply {
                addAll(_productsInCart.value)
                applyChanges(this)
            }
        }.also { list ->
            updateCostAndCount(list)
        }
    }

    private fun updateCostAndCount(list: List<Pair<Int, Product>>) {
        totalPrice.update {
            list.sumOf { it.first * it.second.price }
        }

        productsTotalCount.update {
            list.sumOf { it.first }
        }
    }
}