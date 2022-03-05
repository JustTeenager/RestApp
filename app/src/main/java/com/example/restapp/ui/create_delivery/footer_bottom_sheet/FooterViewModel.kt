package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.restapp.ui.base.BaseEvent
import com.example.restapp.ui.base.BaseViewModel
import com.example.restapp.data.model.Cart
import com.example.restapp.domain.repository.BuyCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FooterViewModel @Inject constructor(
    private val buyCartRepository: BuyCartRepository
) : BaseViewModel<FooterViewModel.Event>() {

    sealed class Event : BaseEvent() {
        class OnBuyConfirmed(val cart: Cart, val address: String) : Event()
        class OnAddressChanged(val address: String) : Event()
    }

    override fun obtainEvent(event: Event) {
        Log.d("tut_FooterViewModel", "obtaining event $event")
        when (event) {
            is Event.OnBuyConfirmed -> {
                confirmBuy(event.cart, event.address)
            }
            is Event.OnAddressChanged -> {
                changeAddress(event.address)
            }
        }
    }

    private fun changeAddress(address: String) {
        buyCartRepository.setCartAddress(address)
    }

    private fun confirmBuy(cart: Cart, address: String) = viewModelScope.launch {
        buyCartRepository.buyCart(cart.copy(address = address))
    }
}