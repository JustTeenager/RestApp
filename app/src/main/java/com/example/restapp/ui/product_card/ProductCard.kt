package com.example.restapp.ui.product_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.restapp.domain.dto.Product
import com.example.restapp.ui.product_card.ProductCardViewModel.Event.OnImageLoadCompleted
import com.example.restapp.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.accompanist.flowlayout.SizeMode.Expand as Expandable


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
                FlowRow(
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium),
                    mainAxisSpacing = MaterialTheme.spacing.small,
                    crossAxisSpacing = MaterialTheme.spacing.medium,
                    mainAxisSize = Expandable,
                    mainAxisAlignment = FlowMainAxisAlignment.Center
                ) {
                    repeat(4) {
                        Tag(modifier = Modifier.wrapContentSize(), text = "Салат ебаный")
                    }
                }
            }
        }
    }
}