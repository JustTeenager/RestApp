package com.example.restapp.ui.product_card

import androidx.lifecycle.ViewModel
import com.example.restapp.domain.dto.Product
import com.example.restapp.domain.repository.BuyProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor(
    private val buyProductRepository: BuyProductRepository
) : ViewModel() {

    var isImageLoaded = MutableStateFlow(false)

    fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnImageLoadCompleted -> isImageLoaded.value = true
            is Event.OnBuyClick -> onBuyClick(event.data)
        }
    }

    private fun onBuyClick(product: Product) {
        buyProductRepository.buyProduct(product)
    }

    sealed class Event {
        object OnImageLoadCompleted : Event()
        class OnBuyClick(val data: Product) : Event()
    }
}