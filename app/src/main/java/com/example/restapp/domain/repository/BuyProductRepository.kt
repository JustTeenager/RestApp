package com.example.restapp.domain.repository

import com.example.restapp.domain.dto.Product

interface BuyProductRepository {
    fun buyProduct(product: Product)
}