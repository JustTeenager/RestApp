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
                    productType = ProductType.values().map { it.code }.random().toProductType()
                ).also { add(it) }
            }
        }
    }

    enum class ProductType(val code: Int, @StringRes val title: Int) {
        PIZZA(4, R.string.tab_pizza_title),
        PASTA(3, R.string.tab_pasta_title),
        VINE(7, R.string.tab_vine_title),
        SOUP(8, R.string.tab_soup_title),
        DESERT(9, R.string.tab_desert_title)
    }
}