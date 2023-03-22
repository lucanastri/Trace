package com.kizune.trace.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kizune.trace.R

val Heebo = FontFamily(
    Font(R.font.heebo_regular),
    Font(R.font.heebo_bold, FontWeight.Bold)
)

val Typography = Typography(
    defaultFontFamily = Heebo,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 42.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    body1 = TextStyle(
        fontSize = 14.sp
    )
)