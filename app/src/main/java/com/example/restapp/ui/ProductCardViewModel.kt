package com.example.restapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapp.ApiRepository
import com.example.restapp.dto.Product
import com.example.restapp.dto.Welcome
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor(
    private val client: ApiRepository
) : ViewModel() {

    var isImageLoaded = MutableStateFlow(false)

    var product: Product? = null

    fun obtainEvent(event: Event){
        viewModelScope.launch {
            Log.d("tut",client.client.get<Welcome>("https://api.nasa.gov/planetary/apod?api_key=8oxlraa0fkP94Fn3v03NIvAPFLnoWlvtBD8n1QCX").toString())
        }
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