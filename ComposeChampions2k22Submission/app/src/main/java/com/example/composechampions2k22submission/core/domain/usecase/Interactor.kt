package com.example.composechampions2k22submission.core.domain.usecase

import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Interactor @Inject constructor(
    private val repository: IRepository
):UseCase {
    override fun getAnimeCurrentSeasonFromApi(): Flow<Resource<List<Anime>>> =
        repository.getAnimeCurrentSeasonFromApi()

    override fun getAnimeDetailFromApi(id: Int): Flow<Resource<Anime>> =
        repository.getAnimeDetailFromApi(id)

    override suspend fun insertFavouriteAnime(anime: Anime) =
        repository.insertFavouriteAnime(anime)

    override suspend fun deleteFavouriteAnime(anime: Anime) =
        repository.deleteFavouriteAnime(anime)

    override fun getAllFavouritedAnime(): Flow<Resource<List<Anime>>> =
        repository.getAllFavouritedAnime()

}