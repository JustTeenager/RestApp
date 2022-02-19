package com.example.restapp.data.manager

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
                    .map { it.second }
                    .sumOf { it.price }
            }

    val totalPrice = MutableStateFlow(0)

    fun addProductToCart(product: Product) {
        val pairNeeded = _productsInCart.value.find { it.second == product }
        if (pairNeeded != null) {
            val pairNeededIndex = _productsInCart.value.indexOf(pairNeeded)
            val productCount = pairNeeded.first

            updateProductsInCart { list ->
                list.add(pairNeededIndex, pairNeeded.copy(first = productCount + 1))
            }
        } else {
            updateProductsInCart { list ->
                list.add(0 to product)
            }
        }
    }

    fun removeProductFromCart(product: Product) {
        val pairNeeded = _productsInCart.value.find { it.second == product }
        if (pairNeeded != null) {
            val pairNeededIndex = _productsInCart.value.indexOf(pairNeeded)
            val productCount = pairNeeded.first
            updateProductsInCart { list ->
                if (productCount > 1) {
                    list.add(pairNeededIndex, pairNeeded.copy(first = productCount - 1))
                } else {
                    list.remove(pairNeeded)
                }
            }
        }
    }

    private fun updateProductsInCart(applyChanges: (MutableList<Pair<Int, Product>>) -> Unit) {
        _productsInCart.value = mutableListOf<Pair<Int, Product>>().apply {
            addAll(_productsInCart.value)
            applyChanges(this)
        }.toList()
    }
}