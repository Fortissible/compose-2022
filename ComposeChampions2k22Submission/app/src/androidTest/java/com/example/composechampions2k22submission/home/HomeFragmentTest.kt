package com.example.composechampions2k22submission.home

    import androidx.activity.ComponentActivity
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.test.assertIsDisplayed
    import androidx.compose.ui.test.junit4.createAndroidComposeRule
    import androidx.compose.ui.test.onNodeWithContentDescription
    import androidx.compose.ui.test.onNodeWithText
    import androidx.compose.ui.test.performClick
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.ComposeNavigator
    import androidx.navigation.testing.TestNavHostController
    import com.example.composechampions2k22submission.MainScreen
    import com.example.composechampions2k22submission.core.ui.route.Screen
    import com.example.composechampions2k22submission.detail.DetailViewModel
    import com.example.composechampions2k22submission.wishlist.WishListViewModel
    import junit.framework.Assert.assertEquals
    import org.junit.Before
    import org.junit.Rule
    import org.junit.Test

class HomeFragmentTest{
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val homeViewModel:HomeViewModel = viewModel()
            val detailViewModel:DetailViewModel = viewModel()
            val wishListViewModel:WishListViewModel = viewModel()
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(navController = navController,
                homeViewModel = homeViewModel,
                detailViewModel = detailViewModel,
                wishListViewModel = wishListViewModel
            )
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screen.Home.route, currentRoute)
    }

    @Test
    fun navHost_navigateToAboutScreen() {
        composeTestRule.onNodeWithContentDescription("about_page").performClick()
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screen.About.route, currentRoute)
    }
}