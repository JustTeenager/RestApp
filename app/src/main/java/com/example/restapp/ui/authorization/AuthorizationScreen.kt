package com.example.restapp.ui.authorization

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import javax.inject.Inject

@Composable
fun AuthorizationScreen(navGraph: NavHostController) {
    Scaffold() {

    }
}

class AuthorizationScreenFactory @Inject constructor(

): NavigationFactory {

    companion object Companion: NavigationFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}