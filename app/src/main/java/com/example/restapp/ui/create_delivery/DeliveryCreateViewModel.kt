package com.example.restapp.ui.create_delivery

import com.example.restapp.data.model.Cart
import com.example.restapp.domain.repository.BuyCartRepository
import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DeliveryCreateViewModel @Inject constructor(
    buyCartRepository: BuyCartRepository
) : BaseViewModel<Nothing>() {

    var productCart: Flow<Cart?> = buyCartRepository
        .getProductsCart()

    var productCount = buyCartRepository.getProductsCount()

    override fun obtainEvent(event: Nothing) {}

}