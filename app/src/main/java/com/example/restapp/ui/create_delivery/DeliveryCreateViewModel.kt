package com.example.restapp.ui.create_delivery

import androidx.lifecycle.viewModelScope
import com.example.restapp.BaseEvent
import com.example.restapp.BaseViewModel
import com.example.restapp.data.model.Cart
import com.example.restapp.domain.repository.BuyCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryCreateViewModel @Inject constructor(
    private val buyCartRepository: BuyCartRepository
) : BaseViewModel<DeliveryCreateViewModel.Event>() {

    lateinit var productCart: StateFlow<Cart?>

    init {
        viewModelScope.launch {
            productCart = buyCartRepository.getProductsCart()
        }
    }

    override fun obtainEvent(event: Event) {
        when {
            event is Event.OnBuyButtonPressed -> {

            }
        }
    }

    sealed class Event : BaseEvent() {
        object OnBuyButtonPressed : Event()
    }
}