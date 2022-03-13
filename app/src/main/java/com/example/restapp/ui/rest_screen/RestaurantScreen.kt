package com.example.restapp.ui.rest_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationFactoryType
import com.example.restapp.di.navigation.NavigationHostFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.filter
import com.example.restapp.ui.rest_screen.nav_bar.BottomNavBar
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import javax.inject.Inject

@Composable
fun RestaurantScreen(
    modifier: Modifier,
    navigationFactoryList: List<NavigationFactory>
) {
    val controller = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                items = NavItem.NavBarItems.values(),
                navController = controller
            )
        }
    ) {
        NavHost(
            navController = controller,
            startDestination = NavItem.NavBarItems.Products.route
        ) {
            navigationFactoryList
                .forEach { it.create(this, controller) }
        }
    }
}

class RestaurantScreenFactory @Inject constructor(
    private val navigationFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>
) : NavigationHostFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactoryType>
        get() = listOf(NavigationFactoryType.Login)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            RestaurantScreen(
                modifier = Modifier,
                navigationFactoryList = navigationFactorySet
                    .filter(NavigationFactoryType.Restaurant)
            )
        }
    }
}