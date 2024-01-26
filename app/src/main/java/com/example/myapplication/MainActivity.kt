package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Icon

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.Home
import com.example.myapplication.models.NewBooking
import com.example.myapplication.models.Notifications
import com.example.myapplication.models.navigationItems
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.NewBookingScreen
import com.example.myapplication.screens.NotificationsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                var selectedItemFromTabs by rememberSaveable {
                    mutableIntStateOf(0)
                }
                navController.addOnDestinationChangedListener{_,destination,arguments ->
                    val selectedIndex = navigationItems.indexOfFirst { it.route == destination.route }
                    if(selectedIndex != -1){
                        selectedItemFromTabs = selectedIndex
                    }
                }
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                NavigationBarItem(
                                    label = { Text(text = navigationItem.title)},
                                    selected = index == selectedItemFromTabs,
                                    onClick = {
                                        selectedItemFromTabs = index;
                                        scope.launch {
                                            navController.navigate(navigationItem.route)
                                        }
                                    },
                                    icon = {
                                        if (index == selectedItemFromTabs) {
                                            Icon(
                                                imageVector = navigationItem.selectedIcon,
                                                contentDescription = navigationItem.title
                                            )
                                        } else Icon(
                                            imageVector = navigationItem.unselectedIcon,
                                            contentDescription = navigationItem.title
                                        )
                                    })
                            }
                        }
                    }
                ) { paddingValues ->
                    NavHost(navController = navController, startDestination = Home.route) {
                        composable(Home.route) {
                            HomeScreen(paddingValues = paddingValues)
                        }
                        composable(NewBooking.route) {
                            NewBookingScreen(paddingValues = paddingValues)
                        }
                        composable(Notifications.route) {
                            NotificationsScreen(paddingValues = paddingValues)
                        }
                    }
                }
            }
        }
    }
}

