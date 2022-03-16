package com.example.restapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationHostFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import com.example.restapp.ui.theme.RestaurantAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationScreenFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    @Inject
    lateinit var navigationHostFactorySet: @JvmSuppressWildcards Set<NavigationHostFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = NavItem.Authorization.route
                    ) {
                        mutableSetOf<NavigationFactory>().apply {
                            addAll(
                                navigationScreenFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Login)
                            )
                            addAll(
                                navigationHostFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Login)
                            )
                        }.forEach { it.create(this, navController) }
                    }
                }
            }
        }
    }
}