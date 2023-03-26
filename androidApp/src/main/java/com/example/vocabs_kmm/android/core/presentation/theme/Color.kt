package com.example.vocabs_kmm.android.core.presentation.theme


import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.example.vocabs_kmm.core.presentation.Colors

val  LightPurple = Color(Colors.LightPurple)
val  DarkPurple= Color(Colors.DarkPurple)
val  LightGrey = Color(Colors.LightGrey)
val LightGreen = Color(Colors.LightGreen)

val lightColors = lightColors(
    primary = LightGrey,
    background = LightGrey,
    onPrimary = DarkPurple,
    onBackground = DarkPurple,
    surface = LightPurple,
    onSurface = LightGrey,
)

val darkColors = darkColors(
    primary = LightGrey,
    background = DarkPurple,
    onPrimary = DarkPurple,
    onBackground = LightGreen,
    surface = LightPurple,
    onSurface = LightGrey
)