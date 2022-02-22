package com.example.restapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.ui.main_screen.MainScreen
import com.example.restapp.ui.theme.RestaurantAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactorySet: @JvmSuppressWildcards Set<NavigationFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        navigationFactorySet = navigationFactorySet
                    )
                }
            }
        }
    }
}

/*val delivery = CartDTO(
           0,
           listOf(
               1 to ProductDTO(
                   id = 1,
                   "Картошка",
                   100,
                   "",
                   "свежая",
                   listOf("рб"),
                   1
               ),
               1 to ProductDTO(
                   id = 2,
                   "Помидоры",
                   200,
                   "",
                   "красные",
                   listOf("крутота"),
                   1
               ),
               3 to ProductDTO(
                   id = 3,
                   "Питса",
                   300,
                   "",
                   "пепперони",
                   listOf("домашняя"),
                   1
               ),
           ),
           700,
           "",
           null
       )

       val json = Json.encodeToString(Delivery.serializer(), delivery)
       Log.d("JSON IS", json)*/