package com.example.restapp.ui.authorization

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restapp.R
import com.example.restapp.ui.theme.*

@Composable
fun WelcomeText(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.medium),
        text = stringResource(R.string.restaurant_name),
        style = TextStyle(
            color = IceCream,
            fontFamily = monplesir,
            fontSize = 75.sp,
            shadow = Shadow(
                color = BackgroundBlack,
                offset = Offset(1f, 4f),
                blurRadius = 4f
            )
        )
    )

    Spacer(modifier = Modifier.height(65.dp))

    Text(
        text = stringResource(R.string.welcome_title),
        fontFamily = roboto_medium,
        fontSize = 30.sp,
        color = Color.White
    )

    Spacer(modifier = Modifier.height(18.dp))

    Text(
        text = stringResource(R.string.welcome_subtitle),
        fontFamily = roboto_light,
        fontSize = 16.sp,
        color = Color.White
    )
}