package com.example.restapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.restapp.ui.main_screen.MainScreen
import com.example.restapp.ui.theme.RestaurantAppTheme
import com.example.restapp.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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