package com.example.restapp.ui.product_catalog.periphery

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restapp.data.model.Product
import com.example.restapp.ui.theme.ShadedBlack
import com.example.restapp.ui.theme.spacing

@Composable
fun ProductCatalogToolbar(
    scrollOffset: Float,
    onProductTypeSelected: (Product.ProductType) -> Unit,
    currentSelectedType: Product.ProductType
) {

    val toolbarIconAnimate by animateDpAsState(targetValue = 150.dp * scrollOffset)

    val tabsList = Product.ProductType.values()

    Column {

        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(toolbarIconAnimate),
                contentScale = ContentScale.FillWidth,
                model = "https://cooking-24.ru/wp-content/uploads/2021/04/12-12.jpg",
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = ShadedBlack,
                    blendMode = BlendMode.Multiply
                )
            )

            ContactsInfo(
                modifier = Modifier
                    .align(TopEnd)
                    .height(toolbarIconAnimate),
                scrollOffset = scrollOffset
            )
        }

    }

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