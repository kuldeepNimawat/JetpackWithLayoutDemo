package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.compose.jetpackwithlayoutdemo.ui.theme.Blue
import com.compose.jetpackwithlayoutdemo.ui.theme.Grey
import com.compose.jetpackwithlayoutdemo.ui.theme.Magenta
import com.compose.jetpackwithlayoutdemo.ui.theme.Orange
import com.compose.jetpackwithlayoutdemo.ui.theme.Red
import com.compose.jetpackwithlayoutdemo.ui.theme.Voilet
import com.compose.jetpackwithlayoutdemo.ui.theme.Yellow
import kotlinx.coroutines.delay

@Composable
fun AnimationColorComponent() {
    val color = remember { Animatable(Color.Red) }

    LaunchedEffect(Unit) {
        val colors = listOf(
            Color.Gray, Color.Yellow, Color(0xFFFFA500), // Orange
            Color.Blue, Color(0xFF8A2BE2), // Violet
            Color.Green, Color.Magenta
        )
        while (true) { // Infinite loop to keep cycling colors
            for (targetColor in colors) {
                color.animateTo(targetColor, animationSpec = tween(1000)) // Animate each color
                delay(1000) // Wait before transitioning to next color
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(color.value)) {
    }

}