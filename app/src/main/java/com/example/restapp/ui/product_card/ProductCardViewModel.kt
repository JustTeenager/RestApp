package com.example.restapp.ui.product_card

import androidx.lifecycle.ViewModel
import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.domain.repository.BuyProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor(
    private val buyProductRepository: BuyProductRepository
) : ViewModel() {

    fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnBuyClick -> onBuyClick(event.data)
        }
    }

    private fun onBuyClick(product: Product) {
        buyProductRepository.buyProduct(product)
    }

    sealed class Event {
        class OnBuyClick(val data: Product) : Event()
    }
}