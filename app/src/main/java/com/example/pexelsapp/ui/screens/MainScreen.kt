package com.example.pexelsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pexelsapp.ui.components.BottomNavigationBar
import com.example.pexelsapp.ui.routes.Route

@Composable
fun MainScreen(){
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val bottomBar = currentRoute in listOf(Route.Home.route, Route.Bookmarks.route)

    Scaffold(
        bottomBar = {
            if(bottomBar){
                BottomNavigationBar(
                    selectedItem = when (currentRoute){
                        Route.Bookmarks.route -> 1
                        else -> 0
                    },
                    onItemSelected = { index ->
                        val target = if (index == 0) Route.Home.route else Route.Bookmarks.route
                        navController.navigate(target) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding().minus(32.dp))
        ){
            composable(Route.Home.route) {
                HomeScreen(
                    onOpenDetails = { photoId ->
                        navController.navigate(Route.Details.create(photoId))
                    }
                )
            }
            composable(Route.Bookmarks.route) {
                BookmarkScreen(
                    onOpenDetails = { photoId ->
                        navController.navigate(Route.Details.create(photoId))
                    },
                    onGoHome = {
                        navController.navigate(Route.Home.route) {
                            popUpTo(Route.Bookmarks.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(
                route = Route.Details.route,
                arguments = listOf(
                    navArgument(Route.Details.PHOTO_ID) { type = NavType.IntType }
                )
            ) { entry ->
                val id = entry.arguments?.getInt(Route.Details.PHOTO_ID) ?: return@composable
                PhotoDetailsScreen(
                    photoId = id,
                    onBack = { navController.popBackStack() },
                    goToHome = {
                        navController.navigate(Route.Home.route) {
                            popUpTo(Route.Bookmarks.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }

}


