package com.example.restapp.domain.repository

import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO

interface BuyProductRepository {
    fun buyProduct(product: Product)
}