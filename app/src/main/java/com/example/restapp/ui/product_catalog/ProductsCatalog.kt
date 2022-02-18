package com.example.restapp.ui.product_catalog

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.NavigationFactory
import com.example.restapp.di.NavigationFactory.NavigationFactoryCompanion
import com.example.restapp.ui.product_catalog.ProductsCatalogViewModel.LoadingState.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import javax.inject.Inject

@Composable
fun ProductCatalog(
    modifier: Modifier,
    productsCatalogViewModel: ProductsCatalogViewModel = hiltViewModel(),
) {
    var isRefreshNeeded by remember { mutableStateOf(false) }

    val loadingState = productsCatalogViewModel.productsLoadState.collectAsState()

    val productsList = productsCatalogViewModel.productsList.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshNeeded),
        onRefresh = {
            Log.d("tut_event", "event refresh initiated")
            productsCatalogViewModel.obtainEvent(ProductsCatalogViewModel.Event.OnLoadingStarted)
        }
    ) {
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
                isRefreshNeeded = false
            }
            LOAD_FAILED -> {
                FailingProductsLoadScreen(
                    modifier = modifier
                )
                isRefreshNeeded = false
            }
        }
    }
}

class ProductsCatalogNavigationFactory @Inject constructor() : NavigationFactory {

    companion object Companion: NavigationFactoryCompanion<ProductsCatalogNavigationFactory>

    override fun create(navGraph: NavGraphBuilder, builder: NavHostController) {
        navGraph.composable(
            route = route,
        ) {
            ProductCatalog(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}