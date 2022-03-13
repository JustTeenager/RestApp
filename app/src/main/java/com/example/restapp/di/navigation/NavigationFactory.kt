package com.example.restapp.di.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationFactory {

    val factoryType: List<NavigationFactoryType>

    interface NavigationFactoryCompanion {
        val route: String
            get() = with(javaClass) {
                `package`.name + canonicalName
            }
    }

    fun create(
        builder: NavGraphBuilder,
        navGraph: NavHostController
    )
}

enum class NavigationFactoryType {
    Login,
    Restaurant
}

interface NavigationScreenFactory : NavigationFactory

interface NavigationHostFactory : NavigationFactory