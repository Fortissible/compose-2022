package com.example.composechampions2k22submission.utils

import com.example.composechampions2k22submission.core.data.source.local.entity.AnimeEntity
import com.example.composechampions2k22submission.core.data.source.remote.response.Data
import com.example.composechampions2k22submission.core.data.source.remote.response.DataItem
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataMapper {

    fun currentSeasonAnimeResponseToAnimeDomain(animes:List<DataItem?>?): List<Anime> {
        val animeList = mutableListOf<Anime>()
        animes?.map { anime ->
            val genreList = mutableListOf<String>()
            anime?.genres?.map {
                genreList.add(
                    it?.name.toString()
                )
            }

            val studioList = mutableListOf<String>()
            anime?.studios?.map {
                studioList.add(
                    it?.name.toString()
                )
            }

            val animeItem = Anime(
                id = anime?.malId ?: 0,
                images = anime?.images?.jpg?.imageUrl,
                title = anime?.title,
                title_japanese = anime?.titleJapanese,
                type = anime?.type,
                episodes = anime?.episodes ?: 12,
                score = anime?.score,
                synopsis = anime?.synopsis,
                genres = genreList,
                theme_op = null,
                theme_ed = null,
                studios = studioList,
            )
            animeList.add(animeItem)
        }
        return animeList
    }

    fun animeDetailResponseToAnimeDomain(anime: Data?):Anime{
        val genreList = mutableListOf<String>()
        anime?.genres?.map {
            genreList.add(
                it?.name.toString()
            )
        }

        val studioList = mutableListOf<String>()
        anime?.studios?.map {
            studioList.add(
                it?.name.toString()
            )
        }

        val opList = mutableListOf<String>()
        anime?.theme?.openings?.forEach {
            opList.add(it?:"")
        }

        val edList = mutableListOf<String>()
        anime?.theme?.endings?.forEach {
            edList.add(it?:"")
        }

        return Anime(
            id = anime?.malId ?: 0,
            images = anime?.images?.jpg?.imageUrl,
            title = anime?.title,
            title_japanese = anime?.titleJapanese,
            type = anime?.type,
            episodes = anime?.episodes ?: 12,
            score = anime?.score,
            synopsis = anime?.synopsis,
            genres = genreList,
            theme_op = opList,
            theme_ed = edList,
            studios = studioList,
        )
    }

    fun animeDomainToEntity(anime:Anime?):AnimeEntity{
        val jsonGenres = Gson().toJson(anime?.genres)
        val jsonStudios = Gson().toJson(anime?.studios)
        val jsonThemeOp = Gson().toJson(anime?.theme_op)
        val jsonThemeEd = Gson().toJson(anime?.theme_ed)
        return AnimeEntity(
            id = anime?.id ?: 0,
            images = anime?.images,
            title = anime?.title,
            title_japanese = anime?.title_japanese,
            type = anime?.type,
            episodes = anime?.episodes,
            score = anime?.score,
            synopsis = anime?.synopsis,
            genres = jsonGenres,
            theme_op = jsonThemeOp,
            theme_ed = jsonThemeEd,
            studios = jsonStudios,
        )
    }

    fun animeEntityToDomain(animes:List<AnimeEntity>?):List<Anime>{
        val animeList = mutableListOf<Anime>()
        if (!animes.isNullOrEmpty()){
            animes.map { anime ->
                val itemType = object : TypeToken<List<String>>() {}.type
                val genresList = Gson().fromJson<List<String>>(anime.genres,itemType)
                val studioList = Gson().fromJson<List<String>>(anime.studios,itemType)
                val opList = Gson().fromJson<List<String>>(anime.theme_op,itemType)
                val edList = Gson().fromJson<List<String>>(anime.theme_ed,itemType)
                animeList.add(
                    Anime(
                        id = anime.id,
                        images = anime.images,
                        title = anime.title,
                        title_japanese = anime.title_japanese,
                        type = anime.type,
                        episodes = anime.episodes,
                        score = anime.score,
                        synopsis = anime.synopsis,
                        genres = genresList,
                        theme_op = opList,
                        theme_ed = edList,
                        studios = studioList,
                    )
                )
            }
        }
        return animeList
    }
}