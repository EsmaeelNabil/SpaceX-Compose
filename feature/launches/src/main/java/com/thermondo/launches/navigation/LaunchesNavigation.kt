package com.thermondo.launches.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.thermondo.launches.LaunchesScreen
import com.thermondo.launches.LaunchesViewModel

const val launchesNavigationRoute = "launches_route"

fun NavController.navigateToLaunches() {
    this.navigate(launchesNavigationRoute)
}

fun NavGraphBuilder.launchesScreen(onLaunchClick: (String) -> Unit) {
    composable(
        route = launchesNavigationRoute,
    ) {
        LaunchesRoute(onLaunchClick)
    }
}

@Composable
internal fun LaunchesRoute(
    onLaunchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LaunchesViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val cacheSize by viewModel.cacheSize.collectAsStateWithLifecycle()

    val pagedLaunches = viewModel.getPagedLaunches().collectAsLazyPagingItems()

    if (state.isRefreshPagingRequired) {
        pagedLaunches.retry()
        viewModel.resetRefreshPagingState()
    }

    LaunchesScreen(
        modifier = modifier,
        state = state,
        cacheSize = cacheSize,
        pagedLaunches = pagedLaunches,
        onLaunchClick = onLaunchClick,
        onSyncLaunches = { viewModel.syncLaunches() },
        resetErrorState = { viewModel.resetErrorState() }
    )
}
