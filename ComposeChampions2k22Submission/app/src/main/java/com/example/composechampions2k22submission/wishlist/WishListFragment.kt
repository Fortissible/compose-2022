package com.example.composechampions2k22submission.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime

@Composable
fun WishlistScreen(
    modifier: Modifier = Modifier,
    wishlistState: State<Resource<List<Anime>>>,
    gotoDetailFromFav : (Anime) -> Unit,
){

    when(wishlistState.value){
        is Resource.Error -> {
            WishlistError(error = wishlistState.value.message.toString())
        }
        is Resource.Loading -> {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFa9a9a9),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 220.dp)
                )
                Text(
                    text = "Loading anime list...",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top=250.dp)
                )
            }
        }
        is Resource.Success -> {
            if (wishlistState.value.data.isNullOrEmpty()) {
                Text(
                    text = "You dont have any favourite anime yet!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 250.dp)
                        .fillMaxWidth(),
                )
            } else {
                LazyColumn(
                    Modifier
                        .padding(bottom = 55.dp)
                ) {

                    items(items= wishlistState.value.data!!){ animeDetail ->
                        Row(
                            modifier = Modifier
                                .padding(
                                    start = 4.dp,
                                    end = 4.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                )
                                .fillMaxWidth()
                                .background(Color(0xFFFFFFFF))
                                .border(
                                    1.dp, Color(0xFFa4a4a3), RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    gotoDetailFromFav(animeDetail)
                                }
                        ){
                            AsyncImage(
                                model = animeDetail.images,
                                contentDescription = "Favourite Anime Poster",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .width(90.dp)
                                    .height(150.dp),
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = animeDetail.title.toString(),
                                    fontSize = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight(1000),
                                    modifier = Modifier.padding(
                                        top = 6.dp
                                    )
                                )
                                Text(
                                    text = animeDetail.title_japanese.toString(),
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(
                                        top = 4.dp,
                                        bottom = 5.dp
                                    ),
                                    fontWeight = FontWeight(500)
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    items(
                                        items = animeDetail.genres?: listOf(""),
                                    ){
                                        Box(
                                            Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color(0xFFa9a9a9))
                                                .padding(
                                                    top = 4.dp,
                                                ),
                                        ) {
                                            Text(
                                                text = it,
                                            )
                                        }
                                    }
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = animeDetail.synopsis.toString(),
                                        fontSize = 12.sp,
                                        maxLines = 5,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .padding(
                                                top = 6.dp,
                                                bottom = 5.dp
                                            )
                                            .fillMaxWidth(0.95f),
                                        fontWeight = FontWeight(500),
                                        textAlign = TextAlign.Justify
                                    )
//                                    Icon(
//                                        painter = rememberVectorPainter(
//                                            image = Icons.Default.Favorite
//                                        ),
//                                        contentDescription = "Favourite Button",
//                                        modifier = Modifier
//                                            .clickable {
//                                                deleteFavouriteAnime(animeDetail)
//                                            }
//                                            .fillMaxWidth()
//                                            .size(40.dp)
//                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WishlistError(
    error : String?,
){
    Text(
        text = "Sorry, an error happened... :(",
        textAlign = TextAlign.Center
    )
    Text(
        text = "Error Code :",
        textAlign = TextAlign.Center
    )
    Text(
        text = error.toString(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(600)
    )
}
