package com.example.composechampions2k22submission.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "images")
    val images: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "title_japanese")
    val title_japanese: String?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "score")
    val score: Float?,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "genres")
    val genres: String?,

    @ColumnInfo(name = "theme_op")
    val theme_op: String?,

    @ColumnInfo(name = "theme_ed")
    val theme_ed: String?,

    @ColumnInfo(name = "studios")
    val studios: String?,
)


