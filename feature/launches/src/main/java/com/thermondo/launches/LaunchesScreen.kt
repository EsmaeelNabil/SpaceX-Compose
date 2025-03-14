package com.thermondo.launches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.thermondo.designsystem.component.LaunchItem
import com.thermondo.designsystem.component.ThermondoBackground
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.component.TimedVisibility
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.model.data.Launch

@Composable
internal fun LaunchesScreen(
    modifier: Modifier = Modifier,
    state: LaunchesScreenState,
    cacheSize: Int,
    pagedLaunches: LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit = {},
    onSyncLaunches: () -> Unit,
    resetErrorState: () -> Unit,
    onBookmark: (Launch) -> Unit,
    onRemoveBookmark: (Launch) -> Unit,
) {
    ThermondoBackground(modifier = modifier.fillMaxSize()) {

        Box(Modifier.fillMaxSize()) {

            LunchesList(
                pagedLaunches = pagedLaunches,
                onLaunchClick = onLaunchClick,
                onBookmark = onBookmark,
                onRemoveBookmark = onRemoveBookmark,
                getIsBookmarked = { launchId -> state.bookmarkedLaunchesIds.contains(launchId) }
            )

            if (cacheSize == 0) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Sync the Launches at least once to cache them!."
                    )
                    ThermondoButton(
                        onClick = { onSyncLaunches() },
                        text = @Composable { Text(text = "Sync Launches") },
                        leadingIcon = @Composable {
                            Icon(
                                imageVector = ThermondoIcons.Search,
                                contentDescription = null
                            )
                        },
                    )
                }
            }

            if (!state.error.isNullOrEmpty()) {
                Surface(
                    modifier = Modifier
                        .padding(AppTheme.padding.m)
                        .align(Alignment.BottomCenter),
                    shadowElevation = 2.dp,
                    color = Color.Gray.copy(alpha = 0.99f),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    TimedVisibility(visibleDuration = 5000, onTimeFinish = {
                        resetErrorState()
                    }) {
                        Text(
                            modifier = Modifier.padding(AppTheme.padding.l),
                            text = state.error
                        )
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }
    }
}

@Composable
private fun LunchesList(
    modifier: Modifier = Modifier,
    pagedLaunches: LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit,
    onBookmark: (Launch) -> Unit,
    onRemoveBookmark: (Launch) -> Unit,
    getIsBookmarked: (String) -> Boolean,
) {
    LazyColumn(modifier = modifier) {
        items(
            count = pagedLaunches.itemCount,
            key = pagedLaunches.itemKey { it.id },
            contentType = pagedLaunches.itemContentType { "Launch" }
        ) { index ->
            pagedLaunches[index]?.let { launch ->
                LaunchItem(
                    launch = launch,
                    onLaunchClick = onLaunchClick,
                    onBookmark = onBookmark,
                    onRemoveBookmark = onRemoveBookmark,
                    getIsBookmarked = getIsBookmarked
                )
            }
        }
    }
}