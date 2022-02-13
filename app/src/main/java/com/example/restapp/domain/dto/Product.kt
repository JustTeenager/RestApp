package com.example.restapp.domain.dto

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
    companion object {
        val shimmeredProductsList = mutableListOf<Product>().apply {
            repeat(10) { num ->
                Product(
                    id = num,
                    name = "",
                    price = 0,
                    imgUrl = "",
                    description = "",
                    composition = "",
                    productTypeCode = ProductType.PIZZA
                ).also { add(it) }
            }
        }
    }

    enum class ProductType(val code: Int) {
        PIZZA(0),
        DRINKS(1),
        SALADS(2)
    }
}