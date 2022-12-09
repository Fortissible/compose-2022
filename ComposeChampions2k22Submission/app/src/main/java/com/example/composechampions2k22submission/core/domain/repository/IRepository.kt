package com.example.composechampions2k22submission.core.domain.repository

import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAnimeCurrentSeasonFromApi(): Flow<Resource<List<Anime>>>
    fun getAnimeDetailFromApi(id:Int): Flow<Resource<Anime>>
    suspend fun insertFavouriteAnime(anime: Anime)
    suspend fun deleteFavouriteAnime(anime: Anime)
    fun getAllFavouritedAnime(): Flow<Resource<List<Anime>>>
}