package com.example.composechampions2k22submission.core.data.source.local

import com.example.composechampions2k22submission.core.data.source.local.entity.AnimeEntity
import com.example.composechampions2k22submission.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val animeDao: AnimeDao
){
    suspend fun insertFavouriteAnime(movie:AnimeEntity){
        animeDao.insertFavouriteAnime(movie)
    }

    suspend fun deleteFavouriteAnime(movie:AnimeEntity){
        animeDao.deleteFavouriteAnime(movie)
    }

    fun getAllFavouritedAnime(): Flow<List<AnimeEntity>> = animeDao.getAllFavouriteAnime()
}