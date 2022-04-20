package com.example.restapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.restapp.R

val monplesir = FontFamily(
    Font(R.font.monplesir, FontWeight.Medium, style = FontStyle.Italic),
)

val roboto_medium = FontFamily(
    Font(R.font.roboto_medium, FontWeight.Medium, style = FontStyle.Italic),
)

val roboto_light = FontFamily(
    Font(R.font.roboto_light, FontWeight.Medium, style = FontStyle.Italic),
)

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = PrimaryOrange,
    primaryVariant = PrimaryOrange,
    secondary = Salmon,
    background = BackgroundGrey,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = PrimaryOrange,
    onSurface = PrimaryOrange,
)

@Composable
fun RestaurantAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}