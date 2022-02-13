package com.example.restapp.ui.product_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.ui.product_list.ProductsCatalogViewModel.LoadingState.LOAD_IN_PROGRESS
import com.example.restapp.ui.product_list.ProductsCatalogViewModel.LoadingState.LOAD_SUCCEED
import com.example.restapp.ui.product_list.ProductsCatalogViewModel.LoadingState.LOAD_FAILED
import com.example.restapp.ui.theme.spacing

@Composable
fun ProductCatalog(
    modifier: Modifier,
    productsCatalogViewModel: ProductsCatalogViewModel = hiltViewModel()
) {

    val loadingState = productsCatalogViewModel.productsLoadState.collectAsState()

    val productsList = productsCatalogViewModel.productsList.collectAsState()

    when(loadingState.value) {
        LOAD_IN_PROGRESS -> {
            ProductsList(
                modifier = Modifier,
                productsList = productsList.value,
                isShimmerNeeded = true
            )
        }
        LOAD_SUCCEED -> {
            ProductsList(
                modifier = Modifier,
                productsList = productsList.value,
                isShimmerNeeded = false
            )
        }
        LOAD_FAILED -> {

        }
    }
}