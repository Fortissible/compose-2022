package com.example.composechampions2k22submission.core.ui.components

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composechampions2k22submission.core.ui.route.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
