package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun animationCompose(){
    var visibility by remember { mutableStateOf(true) }
    var coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        coroutineScope.launch {
            while (true) {
                visibility = !visibility
                delay(2000)
             }
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        AnimatedVisibility(visible = visibility,
            enter = fadeIn(animationSpec = tween(1100)),
            exit = fadeOut(animationSpec = tween(1100))
        ) {
           Text(text = "Animation Compose Fade In / Fade Out")
        }
    }
}