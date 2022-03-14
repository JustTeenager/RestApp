package com.example.restapp.ui.authorization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.R
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.base.ValidationTextInput
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import com.example.restapp.ui.theme.spacing
import javax.inject.Inject

@Composable
fun AuthorizationScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navController.navigate(NavItem.Restaurant.route) }
    ) {
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Vertical)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ValidationTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeHolderText = stringResource(R.string.authorization_login_placeholder),
                errorText = stringResource(R.string.authorization_login_error),
                checkIfError = { it?.isBlank() ?: false }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            ValidationTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeHolderText = stringResource(R.string.authorization_password_placeholder),
                errorText = stringResource(R.string.authorization_password_error),
                checkIfError = { it?.isBlank() ?: false }
            )


        }
    }
}

class AuthorizationScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Login)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}