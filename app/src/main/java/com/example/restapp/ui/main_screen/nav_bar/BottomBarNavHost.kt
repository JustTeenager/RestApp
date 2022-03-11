package com.example.restapp.ui.main_screen.nav_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.restapp.di.navigation.NavigationFactory

@Composable
fun BottomBarNavHost(
    navController: NavHostController,
    routesSet: Set<NavigationFactory>
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.NavBarItems.Products.route
    ) {
        routesSet.forEach {
            it.create(this, navController)
        }
    }
}