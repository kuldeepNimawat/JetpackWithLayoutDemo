package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BounceAnimation(){
        var currentState : BounceState by remember { mutableStateOf(BounceState.REALESED) }
        var transition = updateTransition(targetState = currentState,label = "bounce")
        val scale : Float by transition.animateFloat {state ->
            if(state == BounceState.PRESSED){
                0.55f
            }else{
                1f
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        currentState = BounceState.PRESSED
                        tryAwaitRelease()
                        currentState = BounceState.REALESED
                    })
                }) {
            
            Image(painter = painterResource(id = R.drawable.ic_headphone), contentDescription = "bounce image",
                modifier = Modifier.height(100.dp).width(100.dp).graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                })

        }
}