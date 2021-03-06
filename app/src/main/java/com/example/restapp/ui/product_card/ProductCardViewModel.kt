package com.example.restapp.ui.product_card

import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.BuyProductRepository
import com.example.restapp.ui.base.BaseEvent
import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor(
    private val buyProductRepository: BuyProductRepository
) : BaseViewModel<ProductCardViewModel.Event>() {

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnProductAdd -> addProductToCart(event.data)
            is Event.OnProductRemove -> removeProductFromCart(event.data)
        }
    }

    private fun addProductToCart(product: Product) {
        buyProductRepository.addProductToCart(product)
    }

    private fun removeProductFromCart(product: Product) {
        buyProductRepository.removeProductFromCart(product)
    }

    fun getProductCount(product: Product): Int {
        return buyProductRepository.getProductCount(product)
    }

    sealed class Event : BaseEvent() {
        class OnProductAdd(val data: Product) : Event()
        class OnProductRemove(val data: Product) : Event()
    }
}