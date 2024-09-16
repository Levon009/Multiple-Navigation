package com.example.jetpack_multiplenavigation.drawerNavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerNavItem(
    val title: String,
    val icon: ImageVector,
    val hasBadge: Boolean,
    val badgeNum: Int
)
