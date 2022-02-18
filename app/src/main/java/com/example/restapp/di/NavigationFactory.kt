package com.example.restapp.di

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationFactory {

    interface NavigationFactoryCompanion <T> {
        val route: String
            get() = with(javaClass) {
                `package`.name + canonicalName
            }
    }

    fun create(
        navGraph: NavGraphBuilder,
        builder: NavHostController
    )
}