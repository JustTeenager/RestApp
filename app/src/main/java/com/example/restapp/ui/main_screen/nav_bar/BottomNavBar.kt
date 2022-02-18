package com.example.restapp.ui.main_screen.nav_bar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    items: Array<NavBarItems>,
    navController: NavController
) {
    val navControllerBackStackEntry = navController.currentBackStackEntry
    val route = navControllerBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items.forEach { tab ->
            BottomNavigationItem(
                modifier = Modifier,
                selected = tab.route == route,
                onClick = {
                    if (tab.route != route) {
                        with(navController) {
                            navigate(tab.route) {
                                launchSingleTop = true
                                popUpTo(graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = null
                    )
                }
            )
        }
    }
}