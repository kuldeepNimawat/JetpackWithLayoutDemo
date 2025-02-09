package com.compose.jetpackwithlayoutdemo.firebase_realtimedata

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun FirebaseRealImageDataUi(){
    Box(modifier = Modifier.fillMaxSize())
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(color = GREEN),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Firebase Realtime Image Update Demo", color = Color.White, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FirebaseLiveImageDataUI(LocalContext.current)
        }
    }
}

@Composable
fun FirebaseLiveImageDataUI(context: Context){

    val message = remember {
        mutableStateOf("")
    }

    val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    val fb_reference = db.getReference("URL")
    fb_reference.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
           val value = snapshot.getValue(String :: class.java)
            message.value = value!!
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show()
        }

    })
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        
        Image(painter = rememberAsyncImagePainter(message.value),
            contentDescription = "image" ,
            modifier = Modifier.size(350.dp))
        
    }
}