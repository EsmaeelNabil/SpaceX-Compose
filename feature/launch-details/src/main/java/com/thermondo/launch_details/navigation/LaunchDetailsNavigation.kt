package com.thermondo.launch_details.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thermondo.launch_details.LaunchDetailsScreen
import com.thermondo.launch_details.LaunchDetailsViewModel

const val LAUNCH_ID = "launchId"

const val launchesDetailsNavigationRoute = "launch_details_route/{${LAUNCH_ID}}"

fun NavController.navigateToLaunchDetails(launchId: String) {
    this.navigate("launch_details_route/$launchId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.launchDetailsScreen() {
    composable(
        route = launchesDetailsNavigationRoute, arguments = listOf(
            navArgument(LAUNCH_ID) { type = NavType.StringType },
        )
    ) {
        val launchId = it.arguments?.getString(LAUNCH_ID) ?: ""
        LaunchDetailsRoute(launchId = launchId)
    }
}

@Composable
internal fun LaunchDetailsRoute(
    modifier: Modifier = Modifier,
    launchId: String,
    viewModel: LaunchDetailsViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(launchId){
        viewModel.getLaunchDetails(launchId)
    }

    LaunchDetailsScreen(modifier = modifier,
        state = state,
        resetErrorState = { viewModel.resetErrorState() })
}
