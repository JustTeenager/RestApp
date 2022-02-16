package com.example.restapp.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int = 0,
    val name: String,
    val price: Int,
    @SerialName("img_url")
    val imgUrl: String,
    val description: String,
    val tags: List<String>,
    @SerialName("product_type")
    val productTypeCode: Int
) {
}