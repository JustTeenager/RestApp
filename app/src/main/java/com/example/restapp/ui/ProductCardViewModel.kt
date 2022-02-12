package com.example.restapp.ui

import androidx.lifecycle.ViewModel
import com.example.restapp.ApiRepository
import com.example.restapp.dto.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
@ViewModelScoped
class ProductCardViewModel @Inject constructor(
    //private val client: ApiRepository
) : ViewModel() {

    var isImageLoaded = MutableStateFlow(false)

    var product: Product? = null

    fun obtainEvent(event: Event){
        //viewModelScope.launch {  client.get<String>("sdasda") }
        when(event) {
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