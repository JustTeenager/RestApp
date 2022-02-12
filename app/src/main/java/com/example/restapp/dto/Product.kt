package com.example.restapp.dto

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int = 0,
    val name: String,
    val price: Int,
    val imgUrl: String,
    val description: String,
    val composition: String,
    val productType: ProductType
) {
    enum class ProductType(val code: Int) {
        PIZZA(0),
        DRINKS(1),
        SALADS(2)
    }
}