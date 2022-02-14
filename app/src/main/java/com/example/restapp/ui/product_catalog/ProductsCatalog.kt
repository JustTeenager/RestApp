package com.example.restapp.ui.product_catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.ui.product_catalog.ProductsCatalogViewModel.LoadingState.*

@Composable
fun ProductCatalog(
    modifier: Modifier,
    productsCatalogViewModel: ProductsCatalogViewModel = hiltViewModel()
) {

    val loadingState = productsCatalogViewModel.productsLoadState.collectAsState()

    val productsList = productsCatalogViewModel.productsList.collectAsState()

    when (loadingState.value) {
        LOAD_IN_PROGRESS -> {
            ProductsList(
                modifier = modifier,
                productsList = productsList.value,
                isShimmerNeeded = true
            )
        }
        LOAD_SUCCEED -> {
            ProductsList(
                modifier = modifier,
                productsList = productsList.value,
                isShimmerNeeded = false
            )
        }
        LOAD_FAILED -> {
            FailingProductsLoadScreen(
                modifier = modifier
            )
        }
    }
}