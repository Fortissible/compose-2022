package com.example.composechampions2k22submission.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composechampions2k22submission.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavouriteAnime(animeEntity: AnimeEntity)

    @Delete
    suspend fun deleteFavouriteAnime(animeEntity: AnimeEntity)

    @Query("SELECT * FROM anime")
    fun getAllFavouriteAnime():Flow<List<AnimeEntity>>
}