package com.compose.jetpackwithlayoutdemo.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerAppComponent() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentScreen = remember {
        mutableStateOf(DrawerAppScreen.SCREEN1)
    }

    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)) {
                DrawerContentComponent(
                    currentScreen = currentScreen,
                    closeDrawer = {
                        coroutineScope.launch { drawerState.close() }
                    }
                )
            }
        },
        content = {
            BodyContentComponent(
                currentScreen = currentScreen.value,
                openDrawer = {
                    coroutineScope.launch { drawerState.open() }
                }
            )
        }
    )
}