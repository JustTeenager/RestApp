package com.example.restapp.ui.product_catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.restapp.R
import com.example.restapp.ui.theme.spacing

@Composable
fun FailingProductsLoadScreen(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
    ) {
        Image(
            modifier = Modifier.scale(0.75f),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_fail),
            contentDescription = "fail_image",
        )

        Text(
            text = stringResource(id = R.string.fail_title),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun FailingProductsLoadScreenPreview() {
    FailingProductsLoadScreen(modifier = Modifier.fillMaxSize())
}