package com.example.restapp.ui.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import javax.inject.Inject

@Composable
fun RegistrationScreen(
    modifier: Modifier,
    navController: NavController,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

    }
}

class RegistrationScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Login)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            RegistrationScreen(
                modifier = Modifier.fillMaxSize(),
                navController = navGraph
            )
        }
    }
}