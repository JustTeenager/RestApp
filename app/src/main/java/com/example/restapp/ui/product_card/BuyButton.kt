package com.example.restapp.ui.product_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.restapp.R
import com.example.restapp.ui.theme.spacing
import com.example.restapp.ui.toRoubles

@Composable
fun BuyButton(
    modifier: Modifier,
    onClick: () -> Unit,
    price: String
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.large)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium)),
        onClick = { onClick() }
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
}