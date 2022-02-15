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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.domain.dto.Product
import com.example.restapp.ui.product_card.ProductCardViewModel.Event.OnImageLoadCompleted
import com.example.restapp.ui.theme.spacing
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
    val isImageLoaded = viewModel.isImageLoaded.collectAsState()
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialTheme.spacing.medium)
            .placeholder(
                visible = !isImageLoaded.value || isShimmerNeed,
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
            ) { viewModel.obtainEvent(OnImageLoadCompleted) }

            AnimatedVisibility(
                modifier = Modifier,
                visible = isExpanded
            ) {
                Column(modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)) {

                    TagList(
                        listOf("Салатец", "Вкуснотища", "Оливочки", "Острый", "100гр")
                    )

                    BuyButton(
                        modifier = Modifier.align(CenterHorizontally),
                        onClick = {
                            viewModel.obtainEvent(ProductCardViewModel.Event.OnBuyClick(product))
                        },
                        price = product.price
                    )
                }
            }
        }
    }
}
