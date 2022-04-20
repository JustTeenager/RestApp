package com.example.restapp.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restapp.ui.theme.PrimaryOrange
import com.example.restapp.ui.theme.PrimaryToGradientOrange
import com.example.restapp.ui.theme.roboto_medium

@Composable
fun GradientedTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    content: @Composable () -> Unit = {
        Text(
            text = text,
            fontFamily = roboto_medium,
            color = Color.White
        )
    },
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            PrimaryOrange,
                            PrimaryToGradientOrange
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}