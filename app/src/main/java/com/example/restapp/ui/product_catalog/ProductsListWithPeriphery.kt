package com.example.restapp.ui.product_catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.restapp.data.model.Product
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsListWithPeriphery(
    modifier: Modifier,
    initialProductsList: List<Product>,
    isShimmerNeeded: Boolean,
    initialProductType: Product.ProductType = Product.ProductType.PIZZA
) {
    var currentSelectedType by remember {
        mutableStateOf(
            initialProductType
        )
    }

    val productList =
        initialProductsList.filter { it.productType == currentSelectedType }

    val scrollState = rememberLazyListState()

    val isCollapseReached = scrollState.firstVisibleItemIndex > 0

    val scrollSpeedModifier: Float =
        with(LocalDensity.current) {
            120.dp.toPx()
        }

    val scrollOffset: Float = min(
        if (isCollapseReached) 0f else 1f,
        1f - (scrollState.firstVisibleItemScrollOffset / scrollSpeedModifier)
    )

    Column {
        ProductCatalogToolbar(
            scrollOffset = scrollOffset,
            currentSelectedType = currentSelectedType,
            onProductTypeSelected = { currentSelectedType = it },
        )

        Spacer(modifier = Modifier.height(2.dp))

        ProductsList(
            modifier = modifier.fillMaxSize(),
            productList = productList,
            isShimmerNeeded = isShimmerNeeded,
            scrollState = scrollState
        )
    }
}