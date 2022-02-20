package com.example.restapp.data.model

import androidx.annotation.StringRes
import com.example.restapp.R
import com.example.restapp.ui.toProductType

data class Product(
    val id: Int = 0,
    val name: String,
    val price: Int,
    val imgUrl: String,
    val description: String,
    val tags: List<String>,
    val productType: ProductType
) {
    companion object {
        val shimmeredProductsList = mutableListOf<Product>().apply {
            repeat(30) { num ->
                Product(
                    id = num,
                    name = "",
                    price = 0,
                    imgUrl = "",
                    description = "",
                    tags = listOf(),
                    productType = (num % ProductType.values().size).toProductType()
                ).also { add(it) }
            }
        }
    }

    enum class ProductType(val code: Int, @StringRes val title: Int) {
        PIZZA(0, R.string.tab_pizza_title),
        DRINKS(1, R.string.tab_drinks_title),
        SALAD(2, R.string.tab_salad_title)
    }
}