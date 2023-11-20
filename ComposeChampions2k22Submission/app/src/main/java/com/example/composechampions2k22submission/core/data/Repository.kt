package com.example.composechampions2k22submission.core.data

import android.util.Log
import com.example.composechampions2k22submission.core.data.source.local.LocalDataSource
import com.example.composechampions2k22submission.core.data.source.remote.RemoteDataSource
import com.example.composechampions2k22submission.core.data.source.remote.network.ApiResponse
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.repository.IRepository
import com.example.composechampions2k22submission.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):IRepository{

    override fun getAnimeCurrentSeasonFromApi(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        when(val movies = remoteDataSource.getAnimeCurrentSeasonFromApi().first()){
            is ApiResponse.Empty -> {
                val emptyMovie = DataMapper.currentSeasonAnimeResponseToAnimeDomain(null)
                emit(Resource.Success(emptyMovie))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(movies.errorMessage))
            }
            is ApiResponse.Success -> {
                val moviesDomain =  DataMapper.currentSeasonAnimeResponseToAnimeDomain(movies.data)
                Log.d("PRINT ANIME LIST", "List :")
                moviesDomain.forEach{ anime ->
                    Log.d("PRINT ANIME", anime.toString())
                }
                emit(Resource.Success(moviesDomain))
            }
        }
    }

    override fun getAnimeDetailFromApi(
        id: Int
    ): Flow<Resource<Anime>> = flow {
        emit(Resource.Loading())
        when(val animeDetail = remoteDataSource.getAnimeDetailFromApi(id).first()){
            is ApiResponse.Empty -> {
                val emptyAnimeDetail = DataMapper.animeDetailResponseToAnimeDomain(null)
                emit(Resource.Success(emptyAnimeDetail))
            }
            is ApiResponse.Error -> emit(Resource.Error(animeDetail.errorMessage))
            is ApiResponse.Success -> {
                val animeDetailDomain = DataMapper.animeDetailResponseToAnimeDomain(animeDetail.data)
                emit(Resource.Success(animeDetailDomain))
            }
        }
    }

    override suspend fun insertFavouriteAnime(anime: Anime) {
        val animeEntity = DataMapper.animeDomainToEntity(anime)
        localDataSource.insertFavouriteAnime(animeEntity)
    }

    override suspend fun deleteFavouriteAnime(anime: Anime) {
        val animeEntity = DataMapper.animeDomainToEntity(anime)
        localDataSource.deleteFavouriteAnime(animeEntity)
    }

    override fun getAllFavouritedAnime() : Flow<Resource<List<Anime>>> = flow{
        emit(Resource.Loading())
        val data = localDataSource.getAllFavouritedAnime().map { animeEntity ->
            DataMapper.animeEntityToDomain(animeEntity)
        }
        emit(Resource.Success(data.first()))
    }

}