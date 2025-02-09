package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.TextUnit

@Composable
fun RotationalAnimation(){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween<Float>(durationMillis = 3000, easing = FastOutLinearInEasing,),)
            )

            Canvas(modifier = Modifier.size(200.dp)){
                rotate(rotation){
                    drawRect(color = Color.Green)
                    drawContext.canvas.nativeCanvas.apply{
                        drawText("Kuldeep",size.width/4, size.height/2,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = 80f
                                textAlign = android.graphics.Paint.Align.LEFT
                            }
                        )
                    }
                }
            }
    })
}