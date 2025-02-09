package com.compose.jetpackwithlayoutdemo.firebasedataupload

import com.google.firebase.database.Exclude

data class Course(
    @Exclude var courseID : String? = "",
    var courseName : String? = "",
    var courseDuration: String? = "",
    var courseDescription: String? = ""
)
