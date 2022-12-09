package com.example.composechampions2k22submission.core.ui.route

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{animeId}/{fromDetail}"){
        fun createRoute(animeId: Int,fromDetail: Boolean) = "detail/$animeId/$fromDetail"
    }
    object Wishlist : Screen("wishlist")
    object About : Screen("profile")
}
