package com.thermondo.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.thermondo.common.toReadableDate
import com.thermondo.designsystem.component.LaunchItem
import com.thermondo.designsystem.component.ThermondoBackground
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.component.TimedVisibility
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.model.data.Launch

@Composable
internal fun BookmarksScreen(
    modifier: Modifier = Modifier,
    state: BookmarksScreenState,
    onLaunchClick: (String) -> Unit = {},
    resetErrorState: () -> Unit,
    onBookmark: (Launch) -> Unit = {},
    onRemoveBookmark: (Launch) -> Unit,
) {
    ThermondoBackground(modifier = modifier.fillMaxSize()) {

        Box(Modifier.fillMaxSize()) {

            LunchesList(
                launches = state.launches,
                onLaunchClick = onLaunchClick,
                onBookmark = onBookmark,
                onRemoveBookmark = onRemoveBookmark,
                getIsBookmarked = { item -> state.bookmarkedLaunchesIds.contains(item) }
            )

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
    launches: List<Launch>,
    onLaunchClick: (String) -> Unit,
    onBookmark: (Launch) -> Unit,
    onRemoveBookmark: (Launch) -> Unit,
    getIsBookmarked: (String) -> Boolean,
) {
    LazyColumn(modifier = modifier) {
        items(launches) { launch ->
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

