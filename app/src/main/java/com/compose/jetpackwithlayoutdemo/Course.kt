package com.compose.jetpackwithlayoutdemo

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Course(
    val title : String,
    @DrawableRes val img : Int,
    val lightColor : Color,
    val mediumCollor : Color,
    val darkColor : Color
)
