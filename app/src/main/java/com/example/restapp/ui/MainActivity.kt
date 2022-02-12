package com.example.restapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.restapp.domain.dto.Product
import com.example.restapp.ui.theme.RestaurantAppTheme
import com.example.restapp.ui.theme.spacing
import com.example.restapp.ui.product_card.ProductCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val producsList = mutableListOf<Product>()
        repeat(25) { num ->
            val product: Product = Product(
                num,
                "Пицца пеперони",
                750,
                "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
                "Превосходная пицца на тонком тесте со всей хуйней которая нужна пицце",
                "cheese && pasta",
                Product.ProductType.PIZZA
            ).also { producsList.add(it) }
        }
        setContent {
            RestaurantAppTheme {

                Column(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.background(Color.Red)) {
                        repeat(2) {
                            ProductCard(
                                modifier = Modifier,
                                product = producsList[it],
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                    // A surface container using the 'background' color from the theme
                    LazyColumn {
                        items(
                            count = producsList.size,
                            key = { producsList[it].id },
                            itemContent = { index ->
                                ProductCard(
                                    modifier = Modifier,
                                    product = producsList[index],
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}