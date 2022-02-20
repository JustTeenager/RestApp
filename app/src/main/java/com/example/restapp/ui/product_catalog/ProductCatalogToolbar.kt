package com.example.restapp.ui.product_catalog

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restapp.data.model.Product
import com.example.restapp.ui.theme.spacing

@Composable
fun ProductCatalogToolbar(
    scrollOffset: Float,
    onProductTypeSelected: (Product.ProductType) -> Unit,
    currentSelectedType: Product.ProductType
) {

    val dpAnimate by animateDpAsState(targetValue = 150.dp * scrollOffset)

    val tabsList = Product.ProductType.values()

    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(dpAnimate),
            contentScale = ContentScale.FillWidth,
            model = "https://cooking-24.ru/wp-content/uploads/2021/04/12-12.jpg",
            contentDescription = null
        )

        TabRow(
            selectedTabIndex = tabsList
                .indexOf(currentSelectedType)
        ) {
            tabsList.forEach {
                Tab(
                    modifier = Modifier,
                    selected = currentSelectedType == it,
                    onClick = {
                        onProductTypeSelected(it)
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.small),
                        text = stringResource(it.title)
                    )
                }
            }
        }
    }
}