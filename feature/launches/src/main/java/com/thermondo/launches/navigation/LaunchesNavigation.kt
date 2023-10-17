/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        pagedLaunches.refresh()
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
