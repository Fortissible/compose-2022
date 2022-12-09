package com.example.composechampions2k22submission.core.domain.model

data class Anime(
    val id:Int?=null,
    val images: String?=null,
    val title: String?=null,
    val title_japanese: String?=null,
    val type: String?=null,
    val episodes: Int?=null,
    val score: Float?=null,
    val synopsis: String?=null,
    val genres: List<String>?=null,
    val theme_op: List<String>?=null,
    val theme_ed: List<String>?=null,
    val studios: List<String>?=null,
)
