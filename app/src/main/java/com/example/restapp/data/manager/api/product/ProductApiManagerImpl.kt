package com.example.restapp.data.manager.api.product

import android.util.Log
import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.ProductApiManager
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

class ProductApiManagerImpl @Inject constructor(
    private val client: HttpClient,
    private val dataStoreManager: DataStoreManager,
    private val dtoToProductMapper: FromDtoToProductMapper,
    private val productToDtoMapper: FromProductToDtoMapper
) : ProductApiManager {

    override suspend fun loadProducts(): List<Product> {
        return client.get<List<ProductDTO>> {
            url("/products/?format=json")
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, dataStoreManager.getProfileToken())
        }.toProductList(dtoToProductMapper)
    }

    override suspend fun buyProductCart(cart: Cart) {
        Log.d("tut", Json.encodeToString(CartDTO.serializer(), cart.toDTOCart(productToDtoMapper)))
        client.post<HttpResponse> {
            url("/carts/")
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, dataStoreManager.getProfileToken())
            body = cart.toDTOCart(productToDtoMapper)
        }
    }
}