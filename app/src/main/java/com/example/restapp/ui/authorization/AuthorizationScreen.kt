package com.example.restapp.ui.authorization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationFactoryType
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import javax.inject.Inject

@Composable
fun AuthorizationScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navController.navigate(NavItem.Restaurant.route) }
    ) {
        Column {
            Text(text = "Это экран авторизации")
        }
    }
}

class AuthorizationScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactoryType>
        get() = listOf(NavigationFactoryType.Login)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}