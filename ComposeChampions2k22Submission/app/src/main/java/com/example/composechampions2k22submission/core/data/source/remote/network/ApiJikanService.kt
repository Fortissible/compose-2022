package com.example.composechampions2k22submission.core.data.source.remote.network

import com.example.composechampions2k22submission.core.data.source.remote.response.AnimeDetailResponse
import com.example.composechampions2k22submission.core.data.source.remote.response.AnimeSeasonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiJikanService {
    @GET("seasons/now")
    suspend fun getAnimeCurrentSeasonFromApi(
    ): AnimeSeasonResponse

    @GET("anime/{anime_id}/full")
    suspend fun getAnimeDetailFromApi(
        @Path("anime_id") id:Int,
    ): AnimeDetailResponse
}