package com.example.restapp.data.mapper

import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.ui.toRoubles
import javax.inject.Inject

class FromDtoToProductMapper @Inject constructor() : (ProductDTO) -> Product {
    override fun invoke(dto: ProductDTO): Product = Product(
        id = dto.id,
        name = dto.name,
        price = dto.price.toRoubles(),
        imgUrl = dto.imgUrl,
        description = dto.description,
        tags = dto.tags,
        productType = checkProductTypeCode(dto.productTypeCode)
    )

    private fun checkProductTypeCode(code: Int): Product.ProductType {
        Product.ProductType.values().forEach {
            if (code == it.code)
                return it
        }
        throw Exception("Wrong Product type code")
    }

}