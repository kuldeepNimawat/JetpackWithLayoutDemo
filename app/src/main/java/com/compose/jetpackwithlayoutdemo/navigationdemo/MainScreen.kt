package com.compose.jetpackwithlayoutdemo.navigationdemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){
        composable(Routes.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(Routes.ProfileScreen.route){
            ProfileScreen()
        }

        composable(Routes.SettingScreen.route +"/{id}"){ navBackStack ->
            val counter = navBackStack.arguments?.getString("id")
            SettingScreen(counter = counter)
        }
    }

}