package com.best.realindonesianflavors.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColors(
    primary = red2,
    primaryVariant = red1,
    secondary = red3

)

private val LightColorScheme = lightColors(
    primary = red2,
    primaryVariant = red1,
    secondary = red3
)

@Composable
fun RealIndonesianFlavorsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}