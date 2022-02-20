package com.example.restapp.ui.product_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.restapp.R
import com.example.restapp.ui.theme.spacing

@Composable
fun BuyButton(
    modifier: Modifier,
    onAddProduct: () -> Unit,
    onRemoveProduct: () -> Unit,
    price: String,
    productsCount: Int,
    isRemoveButtonVisible: Boolean
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.large)
    ) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                .padding(start = MaterialTheme.spacing.large, end = MaterialTheme.spacing.large)
                .weight(1f, fill = !isRemoveButtonVisible)
                .animateContentSize(),
            onClick = {
                onAddProduct()
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.but_btn_title),
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )

                Text(
                    text = price,
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        AnimatedVisibility(visible = isRemoveButtonVisible) {
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = MaterialTheme.spacing.large),
                onClick = {
                    onRemoveProduct()
                },
            ) {
                Text(text = "-")
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterVertically),
            visible = isRemoveButtonVisible) {
            Tag(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxHeight()
                    .padding(end = MaterialTheme.spacing.large),
                text = productsCount.toString()
            )
        }
    }
}