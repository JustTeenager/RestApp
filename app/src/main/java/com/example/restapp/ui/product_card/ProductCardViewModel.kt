package com.example.restapp.ui.product_card

import androidx.lifecycle.ViewModel
import com.example.restapp.domain.dto.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor(
    //private val loadProductsRepository: LoadProductsRepository
) : ViewModel() {

    var isImageLoaded = MutableStateFlow(false)

    var product: Product? = null

    fun obtainEvent(event: Event) {
        when (event) {
            Event.LOAD_IMAGE_COMPLETED -> isImageLoaded.value = true
            Event.ON_BUY_CLICK -> {
                onBuyClick(product)
            }
        }
    }

    private fun onBuyClick(product: Product?) {
        //TODO добавление продукта в репозиторий
    }

    enum class Event {
        LOAD_IMAGE_COMPLETED,
        ON_BUY_CLICK
    }
}