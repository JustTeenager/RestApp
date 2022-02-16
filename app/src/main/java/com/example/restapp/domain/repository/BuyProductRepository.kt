package com.example.restapp.domain.repository

import com.example.restapp.data.model.Product

interface BuyProductRepository {
    fun buyProduct(product: Product)
}