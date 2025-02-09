package com.compose.jetpackwithlayoutdemo.firebaseupdatedata

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackwithlayoutdemo.R
import com.compose.jetpackwithlayoutdemo.firebasedataupload.Course
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.compose.jetpackwithlayoutdemo.ui.theme.JetpackWithLayoutDemoTheme
import com.google.firebase.firestore.FirebaseFirestore

class UpdateCourse : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackWithLayoutDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                            Text(
                                text = "Firebase Update Data Demo",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CourseUpdateUI(
                                LocalContext.current,
                                intent.getStringExtra("courseName"),
                                intent.getStringExtra("courseDuration"),
                                intent.getStringExtra("courseDescription"),
                                intent.getStringExtra("courseID")
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CourseUpdateUI(
    context: Context,
    courseName: String?,
    courseDuration: String?,
    courseDescription: String?,
    courseID: String?
) {

    val courseName = remember {
        mutableStateOf(courseName)
    }

    val courseDuration = remember {
        mutableStateOf(courseDuration)
    }

    val courseDescription = remember {
        mutableStateOf(courseDescription)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = courseName.value.toString(), onValueChange = { courseName.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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

        TextField(
            value = courseDuration.value.toString(), onValueChange = { courseDuration.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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

        TextField(
            value = courseDescription.value.toString(), onValueChange = { courseDescription.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            onClick = {
                if (TextUtils.isEmpty(courseName.value)) {
                    Toast.makeText(context, "Please enter your course name", Toast.LENGTH_SHORT)
                        .show()
                } else if (TextUtils.isEmpty(courseDuration.value)) {
                    Toast.makeText(
                        context,
                        "Please enter your course duration",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (TextUtils.isEmpty(courseDescription.value)) {
                    Toast.makeText(
                        context,
                        "Please enter your course description",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    updateCourseDataToFirebase(
                        courseID,
                        courseName.value,
                        courseDuration.value,
                        courseDescription.value,
                        context
                    )
                }
            },
            colors = ButtonColors(
                containerColor = GREEN,
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = GREEN
            )
        ) {
            Text(
                text = "UPDATE COURSE",
                color = Color.White, modifier = Modifier.padding(8.dp),
                fontSize = 20.sp
            )
        }

    }
}

fun updateCourseDataToFirebase(
    courseID: String?,
    courseName: String?,
    courseDuration: String?,
    courseDescription: String?,
    context: Context) {

    val updatedCourse = Course(courseID, courseName, courseDuration, courseDescription)
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    db.collection("Courses").document(courseID.toString()).set(updatedCourse)
        .addOnSuccessListener {
            Toast.makeText(context, "Course Updated Successful..",Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, CourseDetailsActivity :: class.java))
            (context as? Activity)?.finish()
        }.addOnFailureListener{
            Toast.makeText(context, "Fail to update course : " + it.message,Toast.LENGTH_SHORT).show()
        }

}
