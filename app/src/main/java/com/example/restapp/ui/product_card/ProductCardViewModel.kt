package com.example.restapp.ui.product_card

import com.example.restapp.BaseEvent
import com.example.restapp.BaseViewModel
import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.BuyProductRepository
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

    sealed class Event : BaseEvent() {
        class OnProductAdd(val data: Product) : Event()
        class OnProductRemove(val data: Product) : Event()
    }
}