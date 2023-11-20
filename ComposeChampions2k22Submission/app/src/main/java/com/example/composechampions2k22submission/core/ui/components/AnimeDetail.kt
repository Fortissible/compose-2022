package com.example.composechampions2k22submission.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composechampions2k22submission.core.domain.model.Anime

@Composable
fun AnimeDetail(
    animeDetail : Anime?,
    modifier : Modifier = Modifier,
    addToFavourite : (Anime) -> Unit,
    fromDetail : Boolean,
    deleteFromFavourite: (Anime) -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp)
    )
    {
        AsyncImage(
            model = animeDetail?.images,
            contentDescription = "Anime poster",
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(180.dp)
                .height(300.dp),
            contentScale = ContentScale.Crop,
        )
        Column {
            Text(
                text = animeDetail?.title.toString(),
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(1000)
            )
            Text(
                text = animeDetail?.title_japanese.toString(),
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    top = 4.dp,
                    bottom = 5.dp
                ),
                fontWeight = FontWeight(500)
            )
            Text(
                text = "Opening:",
                modifier = Modifier.padding(
                    top = 10.dp,
                )
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items = animeDetail?.theme_op ?: listOf("")) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFa9a9a9))
                    ) {
                        Text(
                            text = it,
                        )
                    }
                }
            }
            Text(
                text = "Ending:",
                modifier = Modifier.padding(
                    top = 10.dp,
                )
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = animeDetail?.theme_ed ?: listOf(""),
                ) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFa9a9a9))
                    ) {
                        Text(
                            text = it,
                        )
                    }
                }
            }
            Text(
                text = animeDetail?.type.toString(),
                modifier = Modifier.padding(
                    top = 10.dp,
                )
            )
            Text(
                text = animeDetail?.score.toString(),
                modifier = Modifier.padding(
                    top = 10.dp,
                )
            )
            LazyRow(
                modifier = Modifier.padding(
                    top = 10.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items = animeDetail?.studios ?: listOf("")) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFa9a9a9))
                    ) {
                        Text(
                            text = it,
                        )
                    }
                }
            }
            LazyRow(
                modifier = Modifier.padding(
                    top = 10.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items = animeDetail?.genres ?: listOf("")) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFa9a9a9))
                    ) {
                        Text(
                            text = it,
                        )
                    }
                }
            }
        }
    }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = animeDetail?.synopsis.toString(),
                Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 4.dp
                )
            )
        }
        item {
            if (fromDetail)
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp),
                    onClick = {
                        addToFavourite(animeDetail!!)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFa9a9a9)),
                ) {
                    Text(
                        text = "Add to favourite!",
                        color = Color.Black
                    )
                } else
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp),
                    onClick = {
                        deleteFromFavourite(animeDetail!!)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFe9e9e9)),
                ) {
                    Text(
                        text = "Delete from favourite!",
                        color = Color.Black
                    )
                }
        }
    }
}