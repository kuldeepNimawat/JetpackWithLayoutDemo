package com.compose.jetpackwithlayoutdemo

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableTextData(){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        var ShowMore by remember { mutableStateOf(false) }
        val text = "A computer science portal for geeks. It contains well written, well thought and well explained computer science and programming articles, quizzes and practice/competitive programming/company interview Questions."

            Column( modifier = Modifier.padding(20.dp)) {
                Column(modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(200)
                    )
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) {
                        ShowMore = !ShowMore
                    }) {

                    if(ShowMore){
                        Text(text = text)
                    }else{
                        Text(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
    }
}