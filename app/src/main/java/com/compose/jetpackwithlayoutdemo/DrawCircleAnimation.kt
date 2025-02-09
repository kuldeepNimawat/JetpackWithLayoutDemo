package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DrawCircleAnimation(){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val radius = 200f

        val animateFloat = remember { Animatable(0f) }
        LaunchedEffect(animateFloat){
            animateFloat.animateTo(1f,
                animationSpec = tween(3000, easing = LinearEasing)
            )
        }

        Row {
            Canvas(modifier = Modifier
                .height(100.dp)
                .width(100.dp)){
                drawArc(color = Color.Black,
                    startAngle = 0f,
                    sweepAngle = 360*animateFloat.value,
                    useCenter = true,
                    size = Size(radius*2, radius*2),
                    style = Stroke(2.0f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(100.dp))

        Row{
            Canvas(modifier = Modifier.width(100.dp).height(100.dp)){
                drawArc(
                    color = Color.Green,
                    startAngle = 0f,
                    sweepAngle = 360*animateFloat.value,
                    useCenter = false,
                    size = Size(radius*2, radius*2),
                    style = Stroke(5.0f)
                )
            }
        }

    }
}