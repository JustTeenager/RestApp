package com.example.restapp.data.mapper

import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toProductType
import javax.inject.Inject

class FromDtoToProductMapper @Inject constructor() : (ProductDTO) -> Product {
    override fun invoke(dto: ProductDTO): Product = Product(
        id = dto.id,
        name = dto.name,
        price = dto.price,
        imgUrl = dto.imgUrl,
        description = dto.description,
        tags = dto.tags,
        productType = dto.productTypeCode.toProductType()
    )

}