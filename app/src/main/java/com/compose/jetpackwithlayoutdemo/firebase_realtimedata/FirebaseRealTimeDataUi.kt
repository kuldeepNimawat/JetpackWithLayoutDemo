package com.compose.jetpackwithlayoutdemo.firebase_realtimedata

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

@Composable
fun FirebaseRealTimeDataUi(){
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
            Text(text = "Firebase Realtime Data Update Demo", color = Color.White, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FirebaseLiveDataUI(LocalContext.current)
        }
    }
}

@Composable
fun FirebaseLiveDataUI(context: Context){

    val message = remember {
        mutableStateOf("")
    }

    val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    val dbReference = db.getReference("Data")
    dbReference.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val value = snapshot.getValue(String ::class.java)
            message.value = value!!
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context,"Failed to retreive data", Toast.LENGTH_SHORT).show()
        }
    })
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
         Text(text = "Retrieve Data from Firebase Realtime Database in Android",
             textAlign = TextAlign.Center,
             color = GREEN,
             fontWeight = FontWeight.Bold,
             fontFamily = FontFamily.Default,
             fontSize = 20.sp)
        
        Text(text = message.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
    }
}