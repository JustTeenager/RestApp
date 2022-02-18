package com.example.restapp.ui.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.main_screen.nav_bar.BottomBarNavHost
import com.example.restapp.ui.main_screen.nav_bar.BottomNavBar
import com.example.restapp.ui.main_screen.nav_bar.NavBarItems

@Composable
fun MainScreen(
    modifier: Modifier,
    navigationFactorySet: Set<NavigationFactory>
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = NavBarItems.values(),
                navController = navController
            )
        }
    ) {
        BottomBarNavHost(
            navController = navController,
            routesSet = navigationFactorySet
        )
    }
}