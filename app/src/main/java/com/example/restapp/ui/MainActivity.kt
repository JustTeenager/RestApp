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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.restapp.domain.dto.Product
import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.ui.product_card.ProductCard
import com.example.restapp.ui.theme.RestaurantAppTheme
import com.example.restapp.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RestaurantAppTheme {

                var productsList by remember {
                    mutableStateOf(emptyList<Product>())
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.background(Color.Red)) {
                        repeat(2) {
                            ProductCard(
                                modifier = Modifier,
                                product = productsList[it],
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                    // A surface container using the 'background' color from the theme
                    LazyColumn {
                        items(
                            count = productsList.size,
                            key = { productsList[it].id },
                            itemContent = { index ->
                                ProductCard(
                                    modifier = Modifier,
                                    product = productsList[index],
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}