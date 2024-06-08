package com.example.cathaybankairport.main.navigationbar

import androidx.compose.ui.graphics.vector.ImageVector

data class MyBottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)