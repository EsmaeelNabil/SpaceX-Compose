package com.thermondo.androidchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thermondo.launches.navigation.launchesNavigationRoute
import com.thermondo.launches.navigation.launchesScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = launchesNavigationRoute,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        launchesScreen(onLaunchClick = {})
        // add rest of the screens routs
    }
}
