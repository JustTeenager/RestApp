package com.example.restapp.data.model

import com.example.restapp.domain.dto.ProductDTO
import kotlinx.serialization.SerialName

data class Cart (
    val id: Int,
    @SerialName("product_list")
    val productList: List<Pair<Int, ProductDTO>>,
    @SerialName("total_price")
    val totalPrice: Int,
    val address: String,
    val deliveryStateCode: DeliveryState? = null
) {
    enum class DeliveryState {
        IN_QUEUE,
        COOKING,
        ON_THE_WAY,
        DELIVERED
    }
}
