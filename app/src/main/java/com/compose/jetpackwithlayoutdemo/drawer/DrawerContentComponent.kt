package com.compose.jetpackwithlayoutdemo.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContentComponent(
    currentScreen: MutableState<DrawerAppScreen>,
    closeDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
    ) {
            DrawerAppScreen.values().forEach { screen ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = {
                                currentScreen.value = screen
                                closeDrawer()
                            }),
                        color = if (currentScreen.value == screen) {
                            MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ) {
                        Text(text = screen.name, modifier = Modifier.padding(16.dp))
                    }
        }
    }

}

/*fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> DrawerAppScreen.SCREEN1
    1 -> DrawerAppScreen.SCREEN2
    2 -> DrawerAppScreen.SCREEN3
    else -> DrawerAppScreen.SCREEN1
}*/

