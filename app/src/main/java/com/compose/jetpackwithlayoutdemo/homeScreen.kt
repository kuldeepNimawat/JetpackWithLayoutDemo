package com.compose.jetpackwithlayoutdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Math.abs
import androidx.compose.foundation.Canvas

@Composable
fun homeScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column {
            GreetingSection()
            ChipsSection(chips = listOf("Data structures", "Algorithms", "competitive programming", "python"))
            SuggestionSection()

            CourseSection(
                course = listOf(
                    Course(
                        title = "geek of the year",
                        R.drawable.ic_headphone,
                        // these are colors.......
                        colorResource(id = R.color.blueVoilet1),
                        colorResource(id = R.color.blueVoilet2),
                        colorResource(id = R.color.blueVoilet3)
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.ic_videocam,
                        colorResource(id = R.color.lightGreen1),
                        colorResource(id = R.color.lightGreen2),
                        colorResource(id = R.color.lightGreen3)
                    ),
                    Course(
                        title = "Advance python Course",
                        R.drawable.ic_play,
                        colorResource(id = R.color.lightBlue1),
                        colorResource(id = R.color.lightBlue2),
                        colorResource(id = R.color.lightBlue3)
                    ),
                    Course(
                        title = "Advance Java Course",
                        R.drawable.ic_headphone,
                        colorResource(id = R.color.lightBeige1),
                        colorResource(id = R.color.lightBeige2),
                        colorResource(id = R.color.lightBeige3)
                    ),
                    Course(
                        title = "prepare for aptitude test",
                        R.drawable.ic_play,
                        colorResource(id = R.color.lightYellow1),
                        colorResource(id = R.color.lightYellow2),
                        colorResource(id = R.color.lightYellow3)
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.ic_videocam,
                        colorResource(id = R.color.lightGreen1),
                        colorResource(id = R.color.lightGreen2),
                        colorResource(id = R.color.lightGreen3)
                    )
                )
            )
        }

        BottomMenu(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("explore", R.drawable.ic_explore),
                BottomMenuContent("dark mode", R.drawable.ic_moon),
                BottomMenuContent("videos", R.drawable.ic_videocam),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            )
        )
    }
}

@Composable
fun GreetingSection(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "GoodMorning, User", style = MaterialTheme.typography.headlineMedium,
                color = Color.White)
            Text(text = "We wish to have a good day", style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }

        Icon(painter = painterResource(id = R.drawable.ic_search_icon),
            contentDescription = "Search",
            modifier = Modifier.size(24.dp),
            tint = Color.White)

    }
}

@Composable
fun SuggestionSection(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(colorResource(id = R.color.darkGreen))
        .padding(10.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Daily Coding", style = MaterialTheme.typography.headlineMedium,
                    color = Color.White)
                Text(text = "do at least * 3-10 problems/day", style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }

            Box(modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)){
                Icon(painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(40.dp)
                        .background(colorResource(id = R.color.green)),
                    tint = Color.White)
            }

        }
    }
}

@Composable
fun ChipsSection(
    chips : List<String>
){
    var selectedChipIndex by remember { mutableStateOf(0) }

    LazyRow {
        items(chips.size){
                Box(modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        // this is basic condition for selected chip index
                        if (selectedChipIndex == it) colorResource(id = R.color.green)
                        else colorResource(id = R.color.darkGreen)
                    )
                    .padding(10.dp)
                ){
                    Text(text = chips[it], color = Color.White)
                }
        }
    }
}

@Composable
fun BottomMenu(
    items : List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlighsColor : Color = Color.Green,
    activeTextColor : Color = Color.White,
    inactiveTextColor : Color = Color.Gray,
    initialSelectedItemIndex : Int = 0
){
   var selectedItemIndex by remember {
       mutableStateOf(initialSelectedItemIndex)
   }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom) {
        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_700))
                .padding(10.dp)) {

            items.forEachIndexed { index, item ->
                BottomMenuItem(
                    item = item,
                    isSelected = index == selectedItemIndex,
                    activeHighlightColor = activeHighlighsColor,
                    activeTextColor = activeTextColor,
                    inactiveTextColor = inactiveTextColor
                ){
                    selectedItemIndex = index
                }
            }

        }
    }

}

@Composable
fun  BottomMenuItem(
    item : BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Gray,
    onItemClick: () -> Unit){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick()
        }) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) activeHighlightColor else Color.Transparent)
                    .padding(10.dp)
            ){
                  Icon(painter = painterResource(id = item.iconId),
                      contentDescription = item.title,
                      tint = if(isSelected) activeTextColor else inactiveTextColor,
                      modifier = Modifier.size(25.dp))
            }
         
        Text(text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun CourseSection(course : List<Course>){
    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = "Courses",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // it basically tells no. of cells in a row
            contentPadding = PaddingValues(start = 5.dp, end = 5.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(course.size) {
                CourseItem(course = course[it])
            }
        }
    }
}

@Composable
fun CourseItem(course : Course){
    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(course.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply{
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat()/3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() +100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            drawPath(
                path = mediumColoredPath,
                color = course.mediumCollor
            )
            drawPath(
                path = lightColoredPath,
                color = course.lightColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            Text(text = course.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart))

            Icon(painter = painterResource(id = course.img), contentDescription = course.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart))

            Text(text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id = R.color.green))
                    .padding(vertical = 5.dp, horizontal = 15.dp)
            )
        }
    }
}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}