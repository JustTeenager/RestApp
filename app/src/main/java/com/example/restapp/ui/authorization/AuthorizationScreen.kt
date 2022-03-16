package com.example.restapp.ui.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.R
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.authorization.AuthorizationScreenViewModel.AuthorizationState.*
import com.example.restapp.ui.base.ValidationTextInput
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import com.example.restapp.ui.theme.IceCream
import com.example.restapp.ui.theme.Tomato
import com.example.restapp.ui.theme.customFonts
import com.example.restapp.ui.theme.spacing
import javax.inject.Inject

@Composable
fun AuthorizationScreen(
    navController: NavHostController,
    viewModel: AuthorizationScreenViewModel = hiltViewModel()
) {

    val state = viewModel.authorizationState.collectAsState(NoAuthorization)

    var login by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val snackbarHostState = SnackbarHostState()

    LaunchedEffect(key1 = state.value) {
        when (state.value) {
            is Authorized -> {
                navController.navigate(NavItem.Restaurant.route) {

                }
            }
            is AuthorizedError -> {
                snackbarHostState.showSnackbar(
                    message = "Тыкнули Снекбар",
                    duration = SnackbarDuration.Short
                )
            }

            is Registration -> {
                navController.navigate(NavItem.Registration.route)
            }

            is NoAuthorization -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.auth_img),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Tomato.copy(alpha = 0.75f),
                blendMode = BlendMode.Multiply
            ),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier,
                text = stringResource(R.string.restaurant_name),
                style = TextStyle(
                    color = IceCream,
                    fontFamily = customFonts,
                    fontSize = 96.sp,
                    shadow = Shadow(
                        color = IceCream,
                        offset = Offset(1f, 4f),
                        blurRadius = 4f
                    )
                )
            )

            ValidationTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeHolderText = stringResource(R.string.authorization_login_placeholder),
                errorText = stringResource(R.string.authorization_login_error),
                checkIfError = { it?.isBlank() ?: false },
                onValueChanged = { login = it },
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            ValidationTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeHolderText = stringResource(R.string.authorization_password_placeholder),
                errorText = stringResource(R.string.authorization_password_error),
                checkIfError = { it?.isBlank() ?: false },
                onValueChanged = { password = it },
                transformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel.obtainEvent(
                            AuthorizationScreenViewModel.Event.OnLoginPressed(
                                login = login,
                                password = password
                            )
                        )
                    }
                ) {
                    Text(text = stringResource(R.string.login_btn_title))
                }

                Button(
                    onClick = {
                        viewModel.obtainEvent(
                            AuthorizationScreenViewModel.Event.OnRegisterPressed
                        )
                    }
                ) {
                    Text(text = stringResource(R.string.register_btn_title))
                }
            }
        }

        SnackbarHost(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            hostState = snackbarHostState,
        )
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