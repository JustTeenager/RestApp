package com.example.restapp.ui.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.restapp.R
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.di.navigation.NavigationScreenFactory
import com.example.restapp.ui.authorization.AuthorizationScreenViewModel.AuthorizationState.*
import com.example.restapp.ui.base.GradientedTextButton
import com.example.restapp.ui.base.ValidationTextInput
import com.example.restapp.ui.rest_screen.nav_bar.NavItem
import com.example.restapp.ui.theme.BackgroundBlack
import com.example.restapp.ui.theme.PrimaryOrange
import com.example.restapp.ui.theme.roboto_light
import com.example.restapp.ui.theme.spacing
import com.example.restapp.ui.withGradient
import javax.inject.Inject

@Composable
fun AuthorizationScreen(
    navController: NavController,
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

    //TODO навесить экран загрузки
    LaunchedEffect(key1 = state.value) {
        when (state.value) {
            is Authorized -> {
                navController.navigate(NavItem.Restaurant.route) {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
            is AuthorizedError -> {
                snackbarHostState.showSnackbar(
                    message = "Тыкнули Снекбар",
                    duration = SnackbarDuration.Short
                )
            }

            is Registration -> {
                navController.navigate(NavItem.Registration.route) {
                    launchSingleTop = true
                }
            }

            is NoAuthorization -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .withGradient(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to PrimaryOrange.copy(alpha = 0.4f),
                            0.37f to BackgroundBlack
                        ),
                    )
                )
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize(),
                painter = painterResource(R.drawable.register_img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            WelcomeText()

            Spacer(modifier = Modifier.height(16.dp))

            ValidationTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeHolderText = stringResource(R.string.authorization_login_placeholder),
                errorText = stringResource(R.string.authorization_login_error),
                checkIfError = { it?.isBlank() ?: false },
                onValueChanged = { login = it },
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                GradientedTextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = MaterialTheme.spacing.small,
                            bottom = MaterialTheme.spacing.small
                        ),
                    text = stringResource(R.string.login_btn_title),
                    onClick = {
                        viewModel.obtainEvent(
                            AuthorizationScreenViewModel.Event.OnLoginPressed(
                                login = login,
                                password = password
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            viewModel.obtainEvent(
                                AuthorizationScreenViewModel.Event.OnRegisterPressed
                            )
                        },
                    text = stringResource(R.string.register_btn_title),
                    color = PrimaryOrange.copy(alpha = ContentAlpha.medium),
                    fontFamily = roboto_light,
                    fontSize = 15.sp
                )
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