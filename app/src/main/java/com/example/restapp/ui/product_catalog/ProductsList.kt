package com.example.restapp.ui.product_catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.restapp.data.model.Product
import com.example.restapp.ui.product_card.ProductCard

@Composable
fun ProductsList(
    modifier: Modifier,
    productList: List<Product>,
    isShimmerNeeded: Boolean,
    scrollState: LazyListState
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scrollState
    ) {

        items(
            count = productList.size,
            itemContent = { index ->
                ProductCard(
                    modifier = Modifier,
                    product = productList[index],
                    isShimmerNeed = isShimmerNeeded
                )
            }
        )
    }
}