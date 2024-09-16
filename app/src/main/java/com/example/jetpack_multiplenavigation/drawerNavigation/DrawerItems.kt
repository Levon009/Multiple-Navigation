package com.example.jetpack_multiplenavigation.drawerNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatReclineExtra
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SouthEast

fun getDrawerItems() : List<DrawerNavItem> {
    return listOf(
        DrawerNavItem("Home", Icons.Default.Home, false, 0),
        DrawerNavItem("Account", Icons.Default.Face, false, 0),
        DrawerNavItem("SnackBar", Icons.Default.Favorite, true, 2),
        DrawerNavItem("Room", Icons.Default.Email, true, 32),
        DrawerNavItem("Room_Many", Icons.Default.Phone, true, 4),
        DrawerNavItem("UploadFile Retro", Icons.Default.FileUpload, true, 7),
        DrawerNavItem("TimerService", Icons.Default.Settings, true, 12),
        DrawerNavItem("Shared Preferences", Icons.Default.SouthEast, true, 77),
        DrawerNavItem("Permissions", Icons.Default.AirlineSeatReclineExtra, false, 0),
        DrawerNavItem("Test Receiver", Icons.Default.Security, false, 0)
    )
}