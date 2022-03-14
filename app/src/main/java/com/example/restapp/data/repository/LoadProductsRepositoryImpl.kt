package com.example.restapp.data.repository

import com.example.restapp.data.manager_contracts.ProductApiManager
import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.ui.runRequest
import javax.inject.Inject
import javax.inject.Named

class LoadProductsRepositoryImpl @Inject constructor(
    @Named("Mock") mockProductApiManager: ProductApiManager,
    @Named("Api") productApiManager: ProductApiManager
) : LoadProductsRepository {

    private val isMockUsing = true

    private val manager: ProductApiManager =
        if (isMockUsing) mockProductApiManager else productApiManager

    override suspend fun loadProducts(): Result<List<Product>> =
        runRequest {
            manager.loadProducts()
        }
}