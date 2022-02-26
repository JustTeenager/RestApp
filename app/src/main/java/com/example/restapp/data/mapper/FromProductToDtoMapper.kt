package com.example.restapp.data.mapper

import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO
import javax.inject.Inject

class FromProductToDtoMapper @Inject constructor() : (Product) -> ProductDTO {

    override fun invoke(p1: Product): ProductDTO = ProductDTO(
        id = p1.id,
        name = p1.name,
        price = p1.price,
        imgUrl = p1.imgUrl,
        description = p1.description,
        tags = p1.tags,
        productTypeCode = p1.productType.code
    )
}