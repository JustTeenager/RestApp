package com.example.restapp.ui.product_card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restapp.ui.theme.spacing

@Composable
fun Tag(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(Color.Gray)
            .border(
                1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(MaterialTheme.spacing.medium)
            )
            .padding(6.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1,
        text = text,
        color = Color.White
    )
}

@Preview
@Composable
fun TagPreview() {
    Tag(modifier = Modifier.wrapContentSize(), text = "Питса")
}