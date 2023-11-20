package com.example.composechampions2k22submission.data

import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.repository.IRepository
import com.example.composechampions2k22submission.utils.DummyData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : IRepository {

    var listAnime = DummyData.generateDummyAnimeData()

    override fun getAnimeCurrentSeasonFromApi(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        delay(100)
        emit(Resource.Success(listAnime))
    }

    override fun getAnimeDetailFromApi(id: Int): Flow<Resource<Anime>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavouriteAnime(anime: Anime) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavouriteAnime(anime: Anime) {
        TODO("Not yet implemented")
    }

    override fun getAllFavouritedAnime(): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }
}