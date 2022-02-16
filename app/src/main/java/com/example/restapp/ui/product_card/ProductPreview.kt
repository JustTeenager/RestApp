package com.example.restapp.ui.product_card

import android.R
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.restapp.data.model.Product
import com.example.restapp.ui.theme.spacing

@Composable
fun ProductPreview(
    modifier: Modifier,
    product: Product,
    onImageLoaded: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.imgUrl)
                .crossfade(true)
                .error(R.drawable.stat_notify_error)
                .transformations(CircleCropTransformation())
                .build(),
            onError = {
                onImageLoaded()
                Log.e("tut", "ERRORING")
                it.result.throwable.printStackTrace()
            },
            onSuccess = { onImageLoaded() },
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                .width(100.dp)
                .height(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

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