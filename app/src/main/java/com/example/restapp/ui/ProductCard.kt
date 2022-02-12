package com.example.restapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.transform.CircleCropTransformation
import com.example.restapp.ui.ProductCardViewModel.Event
import com.example.restapp.dto.Product
import com.example.restapp.theme.spacing
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


@Composable
fun ProductCard(
    modifier: Modifier,
    product: Product,
    viewModel: ProductCardViewModel = hiltViewModel()
) {
    val isImageLoaded = viewModel.isImageLoaded.collectAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(MaterialTheme.spacing.medium)
            .clickable { }
            .placeholder(
                visible = !isImageLoaded.value,
                color = Color.White,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = Color.Gray
                )
            ),
        elevation = 16.dp,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium)),
                painter = rememberImagePainter(
                    data = product.imgUrl,
                    builder = {
                        listener(object : ImageRequest.Listener {
                            override fun onSuccess(
                                request: ImageRequest,
                                metadata: ImageResult.Metadata
                            ) {
                                super.onSuccess(request, metadata)
                                viewModel.obtainEvent(Event.LOAD_IMAGE_COMPLETED)
                            }

                            override fun onError(request: ImageRequest, throwable: Throwable) {
                                super.onError(request, throwable)
                                viewModel.obtainEvent(Event.LOAD_IMAGE_COMPLETED)
                            }
                        })
                        error(android.R.drawable.stat_notify_error)
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Column {
                Text(
                    modifier = Modifier,
                    text = product.name,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    modifier = Modifier,
                    text = product.description,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductCartPreview(
    modifier: Modifier = Modifier,
    product: Product = Product(
        0,
        "Пицца пеперони",
        750,
        "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
        "Превосходная пицца на тонком тесте со всей хуйней которая нужна пицце",
        "cheese && pasta",
        Product.ProductType.DRINKS
    ),
    onBuyClick: (Product) -> Unit = { }
) {
    ProductCard(modifier = modifier, product = product)
}