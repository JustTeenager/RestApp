package com.example.restapp.data.repository

import com.example.restapp.data.model.Product
import com.example.restapp.domain.contracts.ApiManager
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.ui.runRequest
import com.example.restapp.ui.toProductList
import javax.inject.Inject
import javax.inject.Named

class LoadProductsRepositoryImpl @Inject constructor(
    @Named("Mock") mockApiManager: ApiManager,
    @Named("Api") apiManager: ApiManager
) : LoadProductsRepository {

    private val isMockUsing = true

    private val manager: ApiManager = if (isMockUsing) mockApiManager else apiManager

    override suspend fun loadProducts(): Result<List<Product>> =
        runRequest {
            manager.loadProducts()
        }
}