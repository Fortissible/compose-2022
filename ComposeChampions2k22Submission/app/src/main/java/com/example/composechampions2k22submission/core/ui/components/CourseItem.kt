package com.example.composechampions2k22submission.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composechampions2k22submission.core.domain.model.Course
import com.example.composechampions2k22submission.core.domain.model.getCourseList
import com.example.composechampions2k22submission.ui.theme.ComposeChampions2k22SubmissionTheme

@Composable
fun CourseItem(
    modifier: Modifier = Modifier,
    course : Course,
){
    if (course.isCompleted == 1){
        Column(
            modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    Color(0x88DAF7A6)
                )
                .padding(
                    8.dp
                )
        ) {
            CardText(course)
        }
    } else {
        Column(
            modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    Color(0x88FF6961)
                )
                .padding(
                    8.dp
                )
        ) {
            CardText(course)
        }
    }

}

@Composable
fun CardText(
    course: Course,
    modifier: Modifier = Modifier
){
    Text(
        text = course.state,
        fontSize = 16.sp,
        fontWeight = FontWeight(750)
    )
    Text(
        modifier = Modifier.fillMaxWidth(0.9f),
        text = course.courseName,
        fontSize = 16.sp,
        fontWeight = FontWeight(370),
        maxLines = 2
    )
}

@Preview(showBackground = true)
@Composable
fun CourseItemPreview(
){
    ComposeChampions2k22SubmissionTheme {
        CourseItem(course = getCourseList()[0])
    }
}