package com.example.restapp.ui.create_delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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

    val cart = deliveryCreateViewModel.productCart

    Column(
        modifier
            .padding(MaterialTheme.spacing.medium),
    ) {
        ProductsList(
            modifier = Modifier,
            productList = cart.value?.productList?.map { it.second } ?: listOf(),
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