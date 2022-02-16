package com.example.restapp.ui.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.restapp.ui.product_catalog.ProductCatalog

@Composable
fun MainScreen(
    modifier: Modifier
) {

    Scaffold {
        ProductCatalog(modifier = Modifier)
    }
}