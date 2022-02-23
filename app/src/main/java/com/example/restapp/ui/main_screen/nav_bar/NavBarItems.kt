package com.example.restapp.ui.main_screen.nav_bar

import androidx.annotation.DrawableRes
import com.example.restapp.R
import com.example.restapp.ui.create_delivery.DeliveryCreateNavigationFactory
import com.example.restapp.ui.delivery_list.DeliveryListNavigationFactory
import com.example.restapp.ui.product_catalog.ProductsCatalogNavigationFactory

enum class NavBarItems(
    @DrawableRes
    val icon: Int,
    val route: String
) {
    Products(
        R.drawable.ic_bottom_bar_product,
        ProductsCatalogNavigationFactory.route
    ),

    DeliveryList(
        R.drawable.ic_bottom_bar_delivery_list,
        DeliveryListNavigationFactory.route
    ),

    CreateDelivery(
        R.drawable.ic_bottom_bar_delivery_create,
        DeliveryCreateNavigationFactory.route
    )
}