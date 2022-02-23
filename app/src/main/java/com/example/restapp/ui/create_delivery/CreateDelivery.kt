package com.example.restapp.ui.create_delivery

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.product_catalog.ProductsList
import com.example.restapp.ui.theme.spacing
import javax.inject.Inject

@Composable
fun DeliveryCreate(
    modifier: Modifier,
    navController: NavController,
    deliveryCreateViewModel: DeliveryCreateViewModel = hiltViewModel()
) {

    val productsInCart by deliveryCreateViewModel
        .productsInCart
        .collectAsState(initial = listOf())

    Column(
        modifier
            .padding(MaterialTheme.spacing.medium),
    ) {
        Log.d("CreateDeliveryProducts", productsInCart.toString())
        ProductsList(
            modifier = Modifier,
            productList = productsInCart.map { it.second },
            isShimmerNeeded = false,
            scrollState = rememberLazyListState()
        )
    }
}

class DeliveryCreateNavigationFactory @Inject constructor() : NavigationFactory {

    companion object Companion :
        NavigationFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            DeliveryCreate(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                navController = navGraph
            )
        }
    }
}