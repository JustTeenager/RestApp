package com.example.restapp.domain.dto

import kotlinx.serialization.SerialName

data class Delivery(
    val id: Int,
    @SerialName("product_list")
    val productList: List<ProductDTO>,
    @SerialName("total_price")
    val totalPrice: Int,
    val address: String,
    val deliveryState: String? = null
)