package com.compose.jetpackwithlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.compose.jetpackwithlayoutdemo.firebase_realtimedata.FirebaseRealImageDataUi
import com.compose.jetpackwithlayoutdemo.firebase_realtimedata.FirebaseRealTimeDataUi
import com.compose.jetpackwithlayoutdemo.firebasedataupload.FirebaseUploadDataDemo
import com.compose.jetpackwithlayoutdemo.firebaseupdatedata.FirebaseUpdateDataDemo
import com.compose.jetpackwithlayoutdemo.ui.theme.JetpackWithLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackWithLayoutDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //-----ConstraintLayout with Compose-----
                    //ConstraintLayoutExample()
                    //-----Scaffold Example----------------
                    //ScaffoldExample()
                    //-----Design practice----
                    //homeScreen()
                    //-----Animation Testing ------
                    //animationCompose() //-----fadeIn/fadeOut effect
                   // AnimationColorComponent()//---background color effect
                   // BounceAnimation() //----button bounce effect------
                   //DrawCircleAnimation()//---progress bar animation-----
                   // RotationalAnimation() //------rotate rectangle --
                  
                    //------shimmer animation effect---------
                /*LazyColumn{
                        repeat(10){
                           item{
                               ShimmerAnimation()
                           }
                        }
                    }*/

                    //ExpandableTextData() //---Expend Text Data
                   //DrawALine()  //-------draw lines--
                    //-------Navigation Demo with Data Sharing-------
                    //MainScreen()
                    //----Menu Drawer---------
                    //DrawerAppComponent()
                    //---Firebase Store Integration--------
                    //FirebaseImplementation()
                    //---Firebase Auth Demo
                    //FirebaseAuthDemo()
                    //---Add Data into Firebase Store
                      //FirebaseUploadDataDemo()
                    //--firebase realtime data update from firebase realtime images without change code------
                     //FirebaseRealImageDataUi()
                    //--firebase realtime data update from firebase change code------
                    //  FirebaseRealTimeDataUi()
                    //---firebase update data and sync to show in details-----
                    FirebaseUpdateDataDemo()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintLayoutExample(){
      ConstraintLayout(
          modifier = Modifier
              .fillMaxSize()
              .background(color = Color.White)
      ) {
          val (logo, topBar, companyName) = createRefs()

          TopAppBar(
              title = { Text("Testing Constraints Layout") },
              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                  containerColor = Color(0xFF0F9D58),
                  titleContentColor = Color.White
              ),
              modifier = Modifier.constrainAs(topBar){
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
          })

          Image(imageVector = Icons.Default.Person, contentDescription ="Company Logo",
              modifier = Modifier
                  .size(70.dp)
                  .constrainAs(logo) {
                      top.linkTo(topBar.bottom)
                      start.linkTo(parent.start)
                      end.linkTo(parent.end)
                      bottom.linkTo(companyName.top)
                  })

          Text(text = "Android Development",
              color = Color.Black,
              modifier = Modifier.constrainAs(companyName){
                  end.linkTo(parent.end)
                  start.linkTo(parent.start)
                  bottom.linkTo(parent.bottom)
              }
          )
      }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Scoffold Title")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
               Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
               Box(
                   modifier = Modifier
                       .padding(paddingValues)
                       .fillMaxSize()
               ){
                   Text(text = "This is my scaffold jetpack compose testing",
                       modifier = Modifier.align(Alignment.Center))
               }
        },
        bottomBar =  {
            BottomAppBar {
                Text(text = "Android Bottom Bar")
            }
        }
    )
}

