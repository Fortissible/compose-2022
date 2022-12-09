package com.example.composechampions2k22submission.core.data.source.remote

import com.example.composechampions2k22submission.core.data.source.remote.network.ApiJikanService
import com.example.composechampions2k22submission.core.data.source.remote.network.ApiResponse
import com.example.composechampions2k22submission.core.data.source.remote.response.Data
import com.example.composechampions2k22submission.core.data.source.remote.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiJikanService
){
    suspend fun getAnimeCurrentSeasonFromApi(): Flow<ApiResponse<List<DataItem?>>>
    = flow {
        try {
            val animeList = apiService.getAnimeCurrentSeasonFromApi().data
            if (animeList.isNullOrEmpty()) emit(ApiResponse.Empty)
            else emit(ApiResponse.Success(animeList))
        } catch (e:Exception){
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAnimeDetailFromApi(id:Int): Flow<ApiResponse<Data?>>
    = flow {
        try {
            val animeDetail = apiService.getAnimeDetailFromApi(id).data
            if (animeDetail?.malId == null) emit(ApiResponse.Empty)
            else emit(ApiResponse.Success(animeDetail))
        } catch (e:Exception){
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}