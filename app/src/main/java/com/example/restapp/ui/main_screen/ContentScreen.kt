package com.example.restapp.ui.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.main_screen.nav_bar.BottomBarNavHost
import com.example.restapp.ui.main_screen.nav_bar.BottomNavBar
import com.example.restapp.ui.main_screen.nav_bar.NavItem
import javax.inject.Inject
import javax.inject.Named

@Composable
fun MainScreen(
    modifier: Modifier,
    navigationFactorySet: Set<NavigationFactory>,
    navController: NavHostController
) {

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                items = NavItem.NavBarItems.values(),
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
class ContentScreenFactory @Inject constructor(
    val navigationFactorySet: @JvmSuppressWildcards Set<NavigationFactory>
): NavigationFactory {

    companion object Companion: NavigationFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            MainScreen(
                modifier = Modifier,
                navigationFactorySet = navigationFactorySet,
                navController = navGraph
            )
        }
    }
}