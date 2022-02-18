package com.example.restapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.restapp.di.NavigationFactory
import com.example.restapp.ui.main_screen.MainScreen
import com.example.restapp.ui.theme.RestaurantAppTheme
import com.example.restapp.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactorySet: @JvmSuppressWildcards Set<NavigationFactory>

    //Проверяем связь с гитлабом
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RestaurantAppTheme {
                //Tag(modifier = Modifier, text = "Острое")
                // A surface container using the 'background' color from the theme
                MainScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.medium)
                )
            }
        }
    }
}