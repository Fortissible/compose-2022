package com.example.composechampions2k22submission.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState : State<Resource<Anime>?>,
    addToFavourite: (Anime) -> Unit,
    deleteFromFavourite:(Anime) -> Unit,
    fromDetail: Boolean,
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(uiState.value){
            is Resource.Error -> {
                Text(
                    text = "Sorry, an error happened... :(",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Error Code :",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = uiState.value?.message.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(600)
                )
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = Color(0xFFa9a9a9),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 250.dp)
                )
                Text(
                    text = "Loading anime detail...",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top=280.dp)
                )
            }
            is Resource.Success -> {
                AnimeDetail(
                    animeDetail = uiState.value?.data,
                    addToFavourite = {
                        addToFavourite(it)
                        navController.navigateUp()
                    },
                    fromDetail=fromDetail,
                    deleteFromFavourite={
                        deleteFromFavourite(it)
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

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
