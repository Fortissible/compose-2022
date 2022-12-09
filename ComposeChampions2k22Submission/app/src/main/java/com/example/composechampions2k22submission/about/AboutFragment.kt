package com.example.composechampions2k22submission.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composechampions2k22submission.R
import com.example.composechampions2k22submission.core.domain.model.getCourseList
import com.example.composechampions2k22submission.core.ui.components.CourseItem


@Composable
fun AboutScreen(
    modifier : Modifier = Modifier,
    navController: NavHostController
){
    val courseList = getCourseList()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pfp_me),
            contentDescription = "Wildan Profile Photo",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    end = 100.dp,
                    start = 100.dp
                )
                .fillMaxWidth()
                .clip(CircleShape)
        )
        Text(
            text = "Wildan Fajri Alfarabi",
            modifier = Modifier.padding(top=16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight(750)
        )
        Text(
            text = "wildanfajrismp259@gmail.com",
            fontWeight = FontWeight(450),
            fontSize = 16.sp
        )
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
        ){
            items(items = courseList){ course ->
                CourseItem(
                    course = course
                )
            }
        }
    }
}