package com.example.restapp.ui.product_catalog

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.product_catalog.ProductsCatalogViewModel.LoadingState.*
import com.example.restapp.ui.product_catalog.products_list.ProductsListWithPeriphery
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
            Log.d("tut_product_catalog", "event refresh initiated")
            productsCatalogViewModel.obtainEvent(ProductsCatalogViewModel.Event.OnLoadingStarted)
        },
        swipeEnabled = loadingState.value != LOAD_IN_PROGRESS
    ) {
        when (loadingState.value) {
            LOAD_IN_PROGRESS -> {
                ProductsListWithPeriphery(
                    modifier = modifier,
                    initialProductsList = productsList.value,
                    isShimmerNeeded = true
                )
                isRefreshNeeded = true
            }
            LOAD_SUCCEED -> {
                ProductsListWithPeriphery(
                    modifier = modifier,
                    initialProductsList = productsList.value,
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

class ProductsCatalogNavigationFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Restaurant)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route,
        ) {
            ProductCatalog(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}