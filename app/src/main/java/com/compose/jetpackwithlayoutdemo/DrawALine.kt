package com.compose.jetpackwithlayoutdemo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawALine(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Canvas(modifier = Modifier.height(20.dp).width(20.dp)){

            val width = size.width
            val height = size.height

            drawLine(color = Color.Black,
                start = Offset(x = width, y=0f),
                end = Offset(x=0f,y=height),
                strokeWidth = 10.0f
            )

            drawLine(color = Color.Black,
                start = Offset(x = 0f, y=0f),
                end = Offset(x=width,y=height),
                strokeWidth = 10.0f
            )

        }
    }
}