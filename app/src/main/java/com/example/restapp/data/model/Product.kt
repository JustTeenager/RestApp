package com.example.restapp.data.model

data class Product(
    val id: Int = 0,
    val name: String,
    val price: String,
    val imgUrl: String,
    val description: String,
    val tags: List<String>,
    val productType: ProductType
) {
    companion object {
        val shimmeredProductsList = mutableListOf<Product>().apply {
            repeat(10) { num ->
                Product(
                    id = num,
                    name = "",
                    price = "0",
                    imgUrl = "",
                    description = "",
                    tags = listOf(),
                    productType = ProductType.PIZZA
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