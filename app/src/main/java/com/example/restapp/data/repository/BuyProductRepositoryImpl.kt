package com.example.restapp.data.repository

import com.example.restapp.data.model.Product
import com.example.restapp.domain.repository.BuyProductRepository
import javax.inject.Inject


class BuyProductRepositoryImpl @Inject constructor(
    //TODO Inject a storageManager
) : BuyProductRepository {

    override fun buyProduct(product: Product) {
        TODO("Not yet implemented")
    }
}