package com.example.restapp.data.manager

import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.model.Product
import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toProductList
import javax.inject.Inject

class MockApiManagerImpl @Inject constructor(
    private val productMapper: FromDtoToProductMapper
) : ApiManager {
    override suspend fun loadProducts(): List<Product> =
        mutableListOf<ProductDTO>().apply {
            repeat(15) { num ->
                ProductDTO(
                    num,
                    "Продукт №$num",
                    num * 10,
                    "https://cooking-24.ru/wp-content/uploads/2021/04/12-12.jpg",
                    "Продукт превосходного содержания",
                    listOf("Салатец", "Вкуснотища", "Оливочки", "Острый", "100гр"),
                    num % Product.ProductType.values().size
                ).also { this.add(it) }
            }
        }.toProductList(productMapper)
}