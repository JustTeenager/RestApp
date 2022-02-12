package com.example.restapp.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int = 0,
    val name: String,
    val price: Int,
    @SerialName("img_url")
    val imgUrl: String,
    val description: String,
    val composition: String,
    @SerialName("product_type_code")
    val productTypeCode: ProductType
) {
    enum class ProductType(val code: Int) {
        PIZZA(0),
        DRINKS(1),
        SALADS(2)
    }
}