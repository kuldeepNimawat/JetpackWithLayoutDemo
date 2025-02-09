package com.compose.jetpackwithlayoutdemo.navigationdemo

sealed class Routes(val route : String){
    object HomeScreen : Routes("home")
    object SettingScreen : Routes("setting")
    object ProfileScreen : Routes("profile")
}
