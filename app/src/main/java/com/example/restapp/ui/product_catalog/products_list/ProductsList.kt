package com.example.restapp.ui.product_catalog.products_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = scrollState,
    ) {

        //TODO Подумать над inner navigation
        items(
            count = productList.size,
            key = { productList[it].id },
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