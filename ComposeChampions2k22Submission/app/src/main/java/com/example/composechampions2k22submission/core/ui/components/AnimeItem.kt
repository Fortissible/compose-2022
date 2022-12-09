package com.example.composechampions2k22submission.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.ui.theme.ComposeChampions2k22SubmissionTheme

@Composable
fun AnimeItem(
    modifier: Modifier = Modifier,
    anime: Anime,
    onClick : (Anime) -> Unit,
){
    Column(
        modifier = modifier
            .padding(
                start = 4.dp,
                end = 4.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(375.dp)
            .background(Color(0xFFFFFFFF))
            .border(
                1.dp, Color(0xFFa4a4a3), RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick(anime)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = anime.images,
            contentDescription = "poster image",
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .size(300.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = anime.title.toString(),
            fontSize = 16.sp,
            maxLines = 1,
            fontWeight = FontWeight(1000),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                horizontal = 10.dp
            )
        )
        Text(
            text = StringBuilder("Score: ")
                .append(anime.score.toString())
                .append("/10").toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(
                top = 2.dp
            )
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun MovieItemPreview(){
    ComposeChampions2k22SubmissionTheme{
        AnimeItem(
            anime = Anime(
                id = 0,
                images = null,
                title =null,
                title_japanese =null,
                type = null,
                episodes = null,
                score = null,
                synopsis = null,
                genres = null,
                theme_op = null,
                theme_ed = null,
                studios = null,
            ),
            onClick = {}
        )
    }
}