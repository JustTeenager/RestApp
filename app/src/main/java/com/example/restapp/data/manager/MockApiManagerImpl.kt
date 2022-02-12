package com.example.restapp.data.manager

import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.Product
import javax.inject.Inject

class MockApiManagerImpl @Inject constructor() : ApiManager {
    override suspend fun loadProducts(): List<Product> =
        mutableListOf<Product>().apply {
            repeat(15) { num ->
                Product(
                    num,
                    "Продукт №$num",
                    num * 10,
                    "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
                    "Продукт превосходного содержания",
                    "cheese && pasta",
                    Product.ProductType.PIZZA
                ).also { this.add(it) }
            }
        }
}