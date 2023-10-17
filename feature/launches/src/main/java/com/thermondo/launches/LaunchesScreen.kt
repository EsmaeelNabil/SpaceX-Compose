package com.thermondo.launches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.thermondo.common.toReadableDate
import com.thermondo.designsystem.component.ThermondoBackground
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.model.data.Launch
import kotlinx.coroutines.delay

@Composable
internal fun LaunchesScreen(
    modifier: Modifier = Modifier,
    state: LaunchesScreenState,
    cacheSize: Int,
    pagedLaunches: LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit = {},
    onSyncLaunches: () -> Unit,
    resetErrorState: () -> Unit,
) {
    ThermondoBackground(modifier = modifier.fillMaxSize()) {

        Box(Modifier.fillMaxSize()) {

            LunchesList(
                pagedLaunches = pagedLaunches,
                onLaunchClick = onLaunchClick
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
    onLaunchClick: (String) -> Unit
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
                    onLaunchClick = onLaunchClick
                )
            }
        }
    }
}

@Composable
private fun LaunchItem(
    modifier: Modifier = Modifier,
    launch: Launch,
    onLaunchClick: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.padding.m)
            .clickable { onLaunchClick(launch.id) }
            .then(modifier),
        shape = RoundedCornerShape(16.dp),
        color = if (launch.success == true)
            Color.Green.copy(alpha = 0.4f)
        else
            Color.Red.copy(alpha = 0.4f)
    ) {

        Row(
            modifier = modifier.padding(AppTheme.padding.m),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = modifier.size(AppTheme.padding.xl),
                model = launch.links.patch.small, contentDescription = null
            )
            Spacer(modifier = modifier.width(AppTheme.padding.m))
            Column {

                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                // open youtube
                            },
                        imageVector = ThermondoIcons.Video,
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(AppTheme.padding.s))
                    Text(
                        modifier = modifier.weight(0.1f),
                        text = launch.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = launch.dateLocal.toReadableDate(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = launch.details.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }
}

// move to a common place, maybe a ui module.
@Composable
fun TimedVisibility(
    visibleDuration: Long,
    onTimeFinish: () -> Unit,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(visibleDuration)
        isVisible = false
        onTimeFinish()
    }

    if (isVisible) {
        content()
    }
}