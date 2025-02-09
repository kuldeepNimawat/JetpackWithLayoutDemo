package com.compose.jetpackwithlayoutdemo.navigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController : NavHostController) {

    var counter by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        contentAlignment = Alignment.Center){

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            
            Text(text = "Home, Counter is $counter", color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
            )
            
            Button(onClick = {counter++}) {
                Text(text = "Increement Counter", color = Color.White)
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))

            Button(onClick = {
                navController.navigate(Routes.ProfileScreen.route)
            }) {
                Text(text = "Navigate To Profile", color = Color.White)
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))

            Button(onClick = {
                navController.navigate(Routes.SettingScreen.route+ "/${counter}")
            }) {
                Text(text = "Navigate To Setting", color = Color.White)
            }
        }

    }

}