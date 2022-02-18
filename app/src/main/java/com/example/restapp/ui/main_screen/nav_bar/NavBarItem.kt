package com.example.restapp.ui.main_screen.nav_bar

sealed class NavBarItem(
    val onClick: () -> Unit,
    val icon: Int,
    val route: String
) {
    class Products(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItem(onClick, icon, route)

    class DeliveryList(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItem(onClick, icon, route)

    class CreateDelivery(
        onClick: () -> Unit,
        icon: Int,
        route: String
    ) : NavBarItem(onClick, icon, route)
}