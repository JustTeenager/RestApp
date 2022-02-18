package com.example.restapp.ui.main_screen.nav_bar

sealed class NavBarItems(
    val onClick: () -> Unit,
    val icon: Int,
    val route: String
) {
    class Products(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItems(onClick, icon, route)

    class DeliveryList(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItems(onClick, icon, route)

    class CreateDelivery(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItems(onClick, icon, route)
}