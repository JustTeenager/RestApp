package com.example.restapp.data.manager

import android.util.Log
import com.example.restapp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageManager @Inject constructor() {

    private val _productsInCart =
        MutableStateFlow(listOf<Pair<Int, Product>>())

    val productsInCart =
        _productsInCart
            .onEach { list ->
                totalPrice.value = list
                    .sumOf { it.first * it.second.price }

                productsCount.value = list.size
            }

    val productsCount = MutableStateFlow(0)
    val totalPrice = MutableStateFlow(0)

    fun addProductToCart(product: Product) {
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
        Log.d("tut_adding_value", _productsInCart.value.toString())
    }

    fun removeProductFromCart(product: Product) {
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

    fun getProductCount(product: Product): Int {
        Log.d(
            "tut_manager_prod_count",
            _productsInCart.value.find { it.second == product }?.first.toString()
        )
        return _productsInCart.value
            .find { it.second == product }?.first ?: 0
    }

    private fun updateProductsInCart(applyChanges: (MutableList<Pair<Int, Product>>) -> Unit) {
        _productsInCart.value = mutableListOf<Pair<Int, Product>>().apply {
            addAll(_productsInCart.value)
            applyChanges(this)
        }.toList()
    }
}