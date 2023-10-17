package com.thermondo.androidchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thermondo.bookmarks.navigation.bookmarksScreen
import com.thermondo.launch_details.navigation.launchDetailsScreen
import com.thermondo.launch_details.navigation.navigateToLaunchDetails
import com.thermondo.launches.navigation.launchesNavigationRoute
import com.thermondo.launches.navigation.launchesScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = launchesNavigationRoute,
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        launchesScreen(onLaunchClick = navController::navigateToLaunchDetails)
        launchDetailsScreen()
        bookmarksScreen(onLaunchClick = navController::navigateToLaunchDetails)
        // add rest of the screens routs
    }
}
