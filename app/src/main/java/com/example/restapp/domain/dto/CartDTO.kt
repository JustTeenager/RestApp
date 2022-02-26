package com.example.restapp.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDTO(
    val id: Int? = null,
    @SerialName("product_list")
    val productList: List<Pair<Int, ProductDTO>>,
    @SerialName("total_price")
    val totalPrice: Int,
    val address: String,
    val deliveryStateCode: Int? = null
)