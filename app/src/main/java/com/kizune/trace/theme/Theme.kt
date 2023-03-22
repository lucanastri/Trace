package com.kizune.trace.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = background_dark,
    onBackground = on_background_dark,
    primary = primary_dark,
    onPrimary = on_primary_dark,
    surface = surface_dark,
    onSurface = on_surface_dark,
    onSecondary = text_hint_dark
)

private val LightColorPalette = lightColors(
    background = background_light,
    onBackground = on_background_light,
    primary = primary_light,
    onPrimary = on_primary_light,
    surface = surface_light,
    onSurface = on_surface_light,
    onSecondary = text_hint_light,
)

@Composable
fun TraceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}