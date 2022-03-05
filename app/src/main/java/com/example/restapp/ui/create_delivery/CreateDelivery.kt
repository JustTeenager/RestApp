package com.example.restapp.ui.create_delivery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.create_delivery.footer_bottom_sheet.FooterBottomSheet
import com.example.restapp.ui.product_catalog.ProductsList
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeliveryCreate(
    modifier: Modifier,
    deliveryCreateViewModel: DeliveryCreateViewModel = hiltViewModel()
) {

    val cart = deliveryCreateViewModel.productCart.collectAsState(null)

    val productList = cart.value?.productList?.map { it.second } ?: listOf()

    val productCount = deliveryCreateViewModel.productCount.collectAsState(0)

    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    FooterBottomSheet(
        modifier = modifier
            .wrapContentSize(),
        productCount = productCount.value,
        cart = cart.value,
        state = state
    ) {
        ProductsList(
            modifier = it
                .wrapContentSize(Alignment.TopStart),
            productList = productList,
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
            )
        }
    }
}