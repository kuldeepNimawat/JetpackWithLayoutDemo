package com.compose.jetpackwithlayoutdemo.firebase

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.annotations.concurrent.Background
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseImplementation(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(android.graphics.Color.GREEN)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Firebase Store", color = Color.White)
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                gridView(LocalContext.current)
            }
        }

    }
}

@Composable
fun gridView(context : Context){
    var courseList = mutableStateListOf<GridModal?>()
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    db.collection("Data").get().addOnSuccessListener {
        if(!it.isEmpty()){
            val list : List<DocumentSnapshot> = it.getDocuments()
            for(doc in list){
                val dataModal : GridModal? = doc.toObject(GridModal::class.java)
                if(dataModal != null){
                    courseList.add(GridModal(name = dataModal.name, imgUrl = dataModal.imgUrl))
                }
            }
        }else{
            Toast.makeText(context, "No data found in Database", Toast.LENGTH_SHORT).show()
        }
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)){
        items(courseList.size){
            Card(onClick = {
                Toast.makeText(context, courseList[it]!!.name+"Selected", Toast.LENGTH_SHORT).show()
            },
                modifier = Modifier.padding(10.dp)) {
                 Column(modifier = Modifier
                     .fillMaxSize()
                     .padding(10.dp),
                     horizontalAlignment = Alignment.CenterHorizontally,
                     verticalArrangement = Arrangement.Center) {
                     /*Image(painter = rememberAsyncImagePainter(courseList[it]!!.imageUrl), contentDescription = "image",
                         modifier = Modifier
                             .wrapContentSize()
                             .wrapContentHeight()
                             .wrapContentWidth())*/

                     AsyncImage(
                         model = courseList[it]!!.imgUrl,
                         contentDescription = "Course Image",
                         modifier = Modifier
                             .size(50.dp)  // Adjust size if needed
                             .clip(RoundedCornerShape(8.dp))
                             .background(Color.Gray) // Helps visualize loading
                             .padding(1.dp),
                         contentScale = ContentScale.Crop
                     )
                     Spacer(modifier = Modifier
                         .fillMaxWidth()
                         .height(8.dp))
                     Text(text = courseList[it]!!.name,
                         modifier = Modifier.padding(2.dp),
                         color = Color.Black
                     )

                 }
            }
        }
    }
}