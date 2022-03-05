package com.example.restapp.ui.product_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.data.model.Product
import com.example.restapp.ui.theme.spacing
import com.example.restapp.ui.toRoubles
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(
    modifier: Modifier,
    product: Product,
    viewModel: ProductCardViewModel = hiltViewModel(),
    isShimmerNeed: Boolean = false,
) {

    var productCount by remember {
        mutableStateOf(viewModel.getProductCount(product))
    }

    var isImageLoaded by remember { mutableStateOf(false) }

    var isExpanded by rememberSaveable { mutableStateOf(false) }

    var localProductType by remember { mutableStateOf(product.productType) }

    val isRemoveButtonVisible = productCount != 0

    var isProductCountUpdateNeededTrigger by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        key1 = isProductCountUpdateNeededTrigger,
        key2 = product.productType
    ) {
        productCount = viewModel.getProductCount(product)
    }

    SideEffect {
        if (product.productType != localProductType) {
            isExpanded = false
            localProductType = product.productType
        }
    }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialTheme.spacing.medium)
            .placeholder(
                visible = !isImageLoaded || isShimmerNeed,
                color = Color.White,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = Color.Gray
                )
            ),
        onClick = { isExpanded = !isExpanded },
        elevation = 16.dp,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
    ) {
        Column {
            ProductPreview(
                Modifier,
                product
            ) { isImageLoaded = true }

            AnimatedVisibility(
                modifier = Modifier,
                visible = isExpanded
            ) {
                Column(modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)) {

                    TagList(
                        product.tags
                    )

                    BuyButton(
                        modifier = Modifier.align(Start),
                        onAddProduct = {
                            viewModel
                                .obtainEvent(
                                    ProductCardViewModel.Event.OnProductAdd(product)
                                )
                            isProductCountUpdateNeededTrigger = !isProductCountUpdateNeededTrigger
                        },
                        onRemoveProduct = {
                            viewModel
                                .obtainEvent(
                                    ProductCardViewModel.Event.OnProductRemove(product)
                                )
                            isProductCountUpdateNeededTrigger = !isProductCountUpdateNeededTrigger
                        },
                        price = product.price.toRoubles(),
                        productsCount = productCount,
                        isRemoveButtonVisible = isRemoveButtonVisible
                    )
                }
            }
        }
    }
}
