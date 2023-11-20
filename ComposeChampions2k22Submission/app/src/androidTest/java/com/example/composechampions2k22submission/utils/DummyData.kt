package com.example.composechampions2k22submission.utils

import com.example.composechampions2k22submission.core.domain.model.Anime

object DummyData {
    fun generateDummyAnimeData() : List<Anime>{
        val animeList = ArrayList<Anime>()
        animeList.addAll(
            listOf(
                Anime(
                    id=40357,
                    images="https://cdn.myanimelist.net/images/anime/1188/136926.jpg",
                    title="Tate no Yuusha no Nariagari Season 3",
                    title_japanese="盾の勇者の成り上がり Season 3",
                    type="TV",
                    episodes=12,
                    score=7.54f,
                    synopsis="After defeating the Spirit Tortoise, Naofumi has no time for rest. An attack from the next Guardian Beast is imminent, but the three other Cardinal Heroes have gone missing. So, Naofumi and his party set out to search for the legendary trio. (Source: Crunchyroll)",
                    genres= listOf("Action", "Adventure", "Drama", "Fantasy"),
                    theme_op=null,
                    theme_ed=null,
                    studios= listOf("Kinema Citrus")
                ),
                Anime(
                    id=47160,
                    images="https://cdn.myanimelist.net/images/anime/1100/138338.jpg",
                    title="Goblin Slayer II",
                    title_japanese="ゴブリンスレイヤーⅡ",
                    type="TV",
                    episodes=12,
                    score=7.53f,
                    synopsis="Second season of Goblin Slayer",
                    genres=listOf("Action", "Adventure", "Fantasy"),
                    theme_op=null,
                    theme_ed=null,
                    studios=listOf("LIDENFILMS")
                ),
                Anime(
                    id=55644,
                    images="https://cdn.myanimelist.net/images/anime/1236/138696.jpg",
                    title="Dr. Stone: New World Part 2",
                    title_japanese="Dr.STONE NEW WORLD",
                    type="TV",
                    episodes=11,
                    score=8.4f,
                    synopsis="Part two of Dr. Stone: New World.",
                    genres=listOf("Adventure", "Comedy", "Sci-Fi"),
                    theme_op=null,
                    theme_ed=null,
                    studios=listOf("TMS Entertainment")
                )
            )
        )
        return animeList
    }
}