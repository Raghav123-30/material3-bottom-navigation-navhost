package com.example.myapplication.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val route:String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home, route = Home.route
    ),
    NavigationItem(
        title = "New Booking", selectedIcon = Icons.Filled.Add, unselectedIcon = Icons.Outlined.Add, route = NewBooking.route
    ),
    NavigationItem(
        title = "Notifications", selectedIcon = Icons.Filled.Notifications, unselectedIcon = Icons.Outlined.Notifications, route = Notifications.route
    )
)