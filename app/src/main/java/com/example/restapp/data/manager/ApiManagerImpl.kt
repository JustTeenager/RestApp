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
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(
    private val client: HttpClient,
    private val dtoToProductMapper: FromDtoToProductMapper,
    private val productToDtoMapper: FromProductToDtoMapper
) : ApiManager {

    override suspend fun loadProducts(): List<Product> {
        return client.get<List<ProductDTO>>("/products/?format=json")
            .toProductList(dtoToProductMapper)
    }

    override suspend fun buyProductCart(cart: Cart) {
        Log.d("tut", Json.encodeToString(CartDTO.serializer(), cart.toDTOCart(productToDtoMapper)))
        client.post<HttpResponse> {
            url("/carts/")
            contentType(ContentType.Application.Json)
            body = cart.toDTOCart(productToDtoMapper)
        }
    }
}