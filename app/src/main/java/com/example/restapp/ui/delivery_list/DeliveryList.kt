package com.example.restapp.ui.delivery_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.theme.spacing
import javax.inject.Inject

@Composable
fun DeliveryList(
    modifier: Modifier,
    navController: NavController
) {
    Box(
        modifier
            .background(Color.Blue)
            .padding(MaterialTheme.spacing.medium)
    ) {}
}

class DeliveryListNavigationFactory @Inject constructor() : NavigationFactory {

    companion object Companion :
        NavigationFactory.NavigationFactoryCompanion<DeliveryListNavigationFactory>

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            DeliveryList(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                navController = navGraph
            )
        }
    }
}