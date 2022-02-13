package com.example.restapp.ui.product_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.restapp.domain.dto.Product
import com.example.restapp.ui.product_card.ProductCard

@Composable
fun ProductsList(
    modifier: Modifier,
    productsList: List<Product>,
    isShimmerNeeded: Boolean
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            count = productsList.size,
            key = { productsList[it].id },
            itemContent = { index ->
                ProductCard(
                    modifier = Modifier,
                    product = productsList[index],
                    isShimmerNeed = isShimmerNeeded
                )
            }
        )
    }
}