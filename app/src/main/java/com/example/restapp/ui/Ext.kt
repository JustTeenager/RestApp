package com.example.restapp.ui

import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO

suspend fun <T> runRequest(method: suspend () -> T): Result<T> {
    return try {
       Result.success(method())
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}

//TODO Стринг ресурс
fun Int.toRoubles(): String {
    return "$this ₽"
}

fun List<ProductDTO>.toProductList(mapper: FromDtoToProductMapper): List<Product> =
    this.map { mapper(it) }