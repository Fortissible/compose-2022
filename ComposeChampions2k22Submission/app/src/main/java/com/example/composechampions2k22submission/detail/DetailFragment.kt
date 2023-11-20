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
import com.example.composechampions2k22submission.core.ui.components.AnimeDetail

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
