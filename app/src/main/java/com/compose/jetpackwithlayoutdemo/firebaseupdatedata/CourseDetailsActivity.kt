package com.compose.jetpackwithlayoutdemo.firebaseupdatedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackwithlayoutdemo.R
import com.compose.jetpackwithlayoutdemo.firebasedataupload.Course
import com.compose.jetpackwithlayoutdemo.firebasedataupload.FirebaseUploadDataUI
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.compose.jetpackwithlayoutdemo.ui.theme.JetpackWithLayoutDemoTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class CourseDetailsActivity : ComponentActivity() {
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
                            Text(text = "Firebase Update Data Demo", color = Color.White, fontSize = 20.sp)
                        }

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CourseDetailUI(LocalContext.current)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CourseDetailUI(context: Context){
    var courseList = mutableStateListOf<Course?>()

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    db.collection("Courses").get().addOnSuccessListener { queryDocumentSnapshots ->

        if(!queryDocumentSnapshots.isEmpty){
            val list = queryDocumentSnapshots.documents
            for(doc in list){
                val course : Course? = doc.toObject(Course :: class.java)
                course?.courseID = doc.id
                courseList.add(course)
            }
        }else{
            Toast.makeText(context, "No Data found in Database", Toast.LENGTH_SHORT).show()
        }
    }.addOnFailureListener{
        Toast.makeText(context, "Fail to get the Data", Toast.LENGTH_SHORT).show()
    }

    ItemDetailUI(context, courseList)
}

@Composable
fun ItemDetailUI(context: Context, courseList: SnapshotStateList<Course?>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
         LazyColumn{
             itemsIndexed(courseList){ index, item ->
                 Card(onClick = {

                                val intent = Intent(context, UpdateCourse  :: class.java)
                                intent.putExtra("courseName", item?.courseName)
                                intent.putExtra("courseDuration", item?.courseDuration)
                                intent.putExtra("courseDescription", item?.courseDescription)
                                intent.putExtra("courseID", item?.courseID)
                                context.startActivity(intent)

                 }, modifier = Modifier.padding(10.dp), elevation = CardDefaults.cardElevation(6.dp)) {

                     Column(modifier = Modifier
                         .fillMaxWidth()
                         .padding(8.dp)) {
                         Spacer(modifier = Modifier.height(5.dp))
                         courseList[index]?.courseName?.let {
                             Text(text = it, color = GREEN,
                                 modifier = Modifier.padding(5.dp),
                                 textAlign = TextAlign.Center,
                                 style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                             )
                         }

                         Spacer(modifier = Modifier.height(5.dp))
                         courseList[index]?.courseDuration?.let {
                             Text(text = it, color = Color.Black,
                                 modifier = Modifier.padding(5.dp),
                                 textAlign = TextAlign.Center,
                                 style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                             )
                         }
                         Spacer(modifier = Modifier.height(5.dp))
                         courseList[index]?.courseDescription?.let {
                             Text(text = it, color = Color.Black,
                                 modifier = Modifier.padding(5.dp),
                                 textAlign = TextAlign.Center,
                                 style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                             )

                         }

                     }

                 }
             }
         }
    }

}
