package com.example.restapp.ui.main_screen.nav_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    modifier: Modifier,
    items: List<NavBarItems>,
    navigationController: NavController
) {
    val navControllerBackStackEntry =  navigationController.currentBackStackEntry
    val route = navControllerBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = modifier
    ) {
        items.forEach {
            BottomNavigationItem(
                selected = it.route == route,
                onClick = it.onClick,
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        contentDescription = null
                    )
                }
            )
        }
    }
}