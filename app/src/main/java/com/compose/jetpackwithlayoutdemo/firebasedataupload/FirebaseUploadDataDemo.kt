package com.compose.jetpackwithlayoutdemo.firebasedataupload

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackwithlayoutdemo.firebaseauth.authenticationView
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun FirebaseUploadDataDemo() {
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
            Text(text = "Firebase Upload Data Demo", color = Color.White, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FirebaseUploadDataUI(LocalContext.current)
        }
    }
}

@Composable
fun FirebaseUploadDataUI(context: Context) {

    val courseName = remember {
        mutableStateOf("")
    }

    val courseDuration = remember {
        mutableStateOf("")
    }

    val courseDescription = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(value = courseName.value, onValueChange = { courseName.value = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            placeholder = {
                Text(
                    text = "Enter your course name",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Gray),
                )
            }, singleLine = true,
            textStyle = TextStyle(fontSize = 14.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = courseDuration.value, onValueChange = { courseDuration.value = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            placeholder = {
                Text(
                    text = "Enter your course duration",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Gray),
                )
            }, singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = courseDescription.value, onValueChange = { courseDescription.value = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            placeholder = {
                Text(
                    text = "Enter your course description",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Gray),
                )
            }, singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(8.dp),
            onClick = {
                if (TextUtils.isEmpty(courseName.value)) {
                    Toast.makeText(context, "Please enter your course name", Toast.LENGTH_SHORT)
                        .show()
                } else if (TextUtils.isEmpty(courseDuration.value)) {
                    Toast.makeText(context, "Please enter your course duration", Toast.LENGTH_SHORT)
                        .show()
                } else if (TextUtils.isEmpty(courseDescription.value)) {
                    Toast.makeText(
                        context,
                        "Please enter your course description",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    addDataToFirebase(
                        courseName.value,
                        courseDuration.value,
                        courseDescription.value,
                        context
                    )
                }
            },
            colors = ButtonColors(containerColor = GREEN, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = GREEN)
        ) {
            Text(
                text = "ADD COURSE",
                color = Color.White,modifier = Modifier.padding(8.dp),
                fontSize = 20.sp
            )
        }

    }

}

fun addDataToFirebase(
    courseName: String,
    courseDuration: String,
    courseDescription: String,
    context: Context
) {
     val db: FirebaseFirestore = FirebaseFirestore.getInstance()
     val dbCourses : CollectionReference = db.collection("Courses")
    val courses = Course(courseName, courseDuration, courseDescription)
    dbCourses.add(courses).addOnSuccessListener {
        Toast.makeText(context, "Your course has been added to Firebase Firestore",Toast.LENGTH_SHORT).show()
    }.addOnFailureListener{error ->
        Toast.makeText(context, "Failed to add course \n$error", Toast.LENGTH_SHORT).show()
    }
}
