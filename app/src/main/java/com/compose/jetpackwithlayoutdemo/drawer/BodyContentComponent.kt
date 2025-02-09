package com.compose.jetpackwithlayoutdemo.drawer

import androidx.compose.runtime.Composable

@Composable
fun BodyContentComponent(
    currentScreen : DrawerAppScreen,
    openDrawer: () -> Unit
){
     when(currentScreen){
         DrawerAppScreen.SCREEN1 -> Screen1Component(openDrawer)
         DrawerAppScreen.SCREEN2 -> Screen2Component(openDrawer)
         DrawerAppScreen.SCREEN3 -> Screen3Component(openDrawer)
     }
}