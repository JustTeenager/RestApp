package com.example.restapp.data.manager

import android.util.Log
import com.example.restapp.data.manager_contracts.ApiManager
import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.mapper.FromProductToDtoMapper
import com.example.restapp.data.model.Cart
import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.CartDTO
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toDTOCart
import com.example.restapp.ui.toProductList
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MockApiManagerImpl @Inject constructor(
    private val dtoToProductMapper: FromDtoToProductMapper,
    private val productToDtoMapper: FromProductToDtoMapper
) : ApiManager {
    override suspend fun loadProducts(): List<Product> =
        mutableListOf<ProductDTO>().apply {
            repeat(50) { num ->
                ProductDTO(
                    num,
                    "Продукт №$num",
                    (num + 1) * 10,
                    "https://cooking-24.ru/wp-content/uploads/2021/04/12-12.jpg",
                    "Продукт превосходного содержания",
                    listOf("Салатец", "Вкуснотища", "Оливочки", "Острый", "100гр"),
                    Product.ProductType.values().map { it.code }.random()
                ).also { this.add(it) }
            }
        }.toProductList(dtoToProductMapper)

    override suspend fun buyProductCart(cart: Cart) {
        Log.d(
            "tut_mock_api_mng",
            Json.encodeToString(CartDTO.serializer(), cart.toDTOCart(productToDtoMapper))
        )
    }
}