package com.example.restapp.ui.create_delivery

import com.example.restapp.BaseEvent
import com.example.restapp.BaseViewModel
import com.example.restapp.domain.repository.BuyProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeliveryCreateViewModel @Inject constructor(
    private val buyProductRepository: BuyProductRepository
) : BaseViewModel<DeliveryCreateViewModel.Event>() {

    val productsInCart = buyProductRepository.getProductsToBuyFlow()

    val totalPrice = buyProductRepository.getTotalCartPrice()

    override fun obtainEvent(event: Event) {
        TODO("Not yet implemented")
    }

    sealed class Event : BaseEvent()
}