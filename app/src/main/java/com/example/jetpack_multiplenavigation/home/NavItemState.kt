package com.example.jetpack_multiplenavigation.home

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemState(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean,
    val badgeNum: Int
)
