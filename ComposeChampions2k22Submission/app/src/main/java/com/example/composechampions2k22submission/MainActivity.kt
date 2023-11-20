package com.example.composechampions2k22submission

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composechampions2k22submission.about.AboutScreen
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.ui.components.NavigationItem
import com.example.composechampions2k22submission.core.ui.route.Screen
import com.example.composechampions2k22submission.detail.DetailScreen
import com.example.composechampions2k22submission.detail.DetailViewModel
import com.example.composechampions2k22submission.home.HomeScreen
import com.example.composechampions2k22submission.home.HomeViewModel
import com.example.composechampions2k22submission.wishlist.WishListViewModel
import com.example.composechampions2k22submission.wishlist.WishlistScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel:HomeViewModel by viewModels()
    private val detailViewModel:DetailViewModel by viewModels()
    private val wishListViewModel:WishListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                homeViewModel = homeViewModel,
                detailViewModel = detailViewModel,
                wishListViewModel = wishListViewModel,
                navController = rememberNavController()
            )
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    wishListViewModel: WishListViewModel,
){
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route
                && currentRoute != Screen.About.route
            )
                BottomBar(
                    navController = navController
                )
        },
        topBar = {
            if (currentRoute != Screen.Detail.route
                && currentRoute != Screen.About.route
            )
            TopBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                val uiState = homeViewModel.getAnimeCurrentSeasonFromApi().observeAsState()
                HomeScreen(
                    navController = navController,
                    uiState = uiState
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments =
                listOf(
                    navArgument("animeId") {
                        type = NavType.IntType
                    },
                    navArgument("fromDetail") {
                        type = NavType.BoolType
                    },
                ),
            ) {
                val id = it.arguments?.getInt("animeId") ?: 0
                val fromDetail = it.arguments?.getBoolean("fromDetail")
                val detailState = detailViewModel.getAnimeDetailFromApi(id)
                    .observeAsState()
                val localContext = LocalContext.current
                DetailScreen(
                    navController = navController,
                    uiState = detailState,
                    addToFavourite = { animeFav ->
                        detailViewModel.insertFavourite(animeFav)
                        Toast.makeText(
                            localContext,
                            "Success adding to favourite...",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    fromDetail = fromDetail!!,
                    deleteFromFavourite = { animeFav ->
                        detailViewModel.deleteFavourite(animeFav)
                        Toast.makeText(
                            localContext,
                            "Success delete anime favourite...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            composable(Screen.Wishlist.route) {
                WishlistScreen(
                    wishlistState = wishListViewModel
                        .getAllFavouritedAnime
                        .collectAsState(
                            initial = Resource.Loading()
                        ),
                    gotoDetailFromFav = {
                        navController.navigate(Screen.Detail.createRoute(it.id!!,false))
                    },
                )
            }
            composable(
                route = Screen.About.route,
            ) {
                AboutScreen(
                    navController = navController,
                )
            }
        }
    }
}



@Composable
fun TopBar(
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFa9a9a9)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "My Anime Reference!",
            color = Color.White,
            fontWeight = FontWeight(750),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    navController.navigate(Screen.About.route)
                }
                .padding(end = 8.dp),
            painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            contentDescription = "about_page"
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    BottomNavigation(
        modifier = modifier,
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        val navItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Favourite",
                icon = Icons.Default.Favorite,
                screen = Screen.Wishlist
            )
        )
        BottomNavigation(
            backgroundColor = Color(0xFFa9a9a9),
            contentColor = Color(0xFF1F1D1D)
        ) {
            navItems.map {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    },
                    label = { Text(it.title) },
                    selected = currentRoute == it.screen.route,
                    onClick = {
                        navController.navigate(it.screen.route)
                        {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}