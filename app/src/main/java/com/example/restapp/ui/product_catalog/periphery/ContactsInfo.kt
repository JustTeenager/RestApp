package com.example.restapp.ui.product_catalog.periphery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.restapp.R
import com.example.restapp.ui.theme.spacing

@Composable
fun ContactsInfo(
    modifier: Modifier,
    scrollOffset: Float
) {

    var isInfoTabVisible by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(MaterialTheme.spacing.small)
            .alpha(scrollOffset)
    ) {

        AnimatedVisibility(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .align(Alignment.CenterVertically)
                .background(Color.White),
            visible = isInfoTabVisible
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .width(IntrinsicSize.Min)
            ) {
                Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(MaterialTheme.spacing.small),
                    text = stringResource(R.string.num_help_text),
                )

                Text(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .wrapContentWidth(),
                    text = stringResource(R.string.addr_help_text)
                )
            }
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.Top),
            onClick = { isInfoTabVisible = !isInfoTabVisible },
            enabled = scrollOffset >= 0.8f
        ) {
            Image(
                modifier = Modifier,
                imageVector = ImageVector.vectorResource(R.drawable.baseline_help_outline_24),
                contentDescription = null,
            )
        }
    }
}