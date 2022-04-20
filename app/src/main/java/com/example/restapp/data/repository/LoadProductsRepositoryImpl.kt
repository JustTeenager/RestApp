package com.example.restapp.data.repository

import com.example.restapp.data.manager_contracts.ProductApiManager
import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.ui.runRequest
import javax.inject.Inject

class LoadProductsRepositoryImpl @Inject constructor(
    private val productApiManager: ProductApiManager,
) : LoadProductsRepository {

    override suspend fun loadProducts(): Result<List<Product>> =
        runRequest {
            productApiManager.loadProducts()
        }
}