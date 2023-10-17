package com.thermondo.launch_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thermondo.common.toReadableDate
import com.thermondo.designsystem.component.ThermondoBackground
import com.thermondo.designsystem.component.TimedVisibility
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.model.data.Launch

@Composable
internal fun LaunchDetailsScreen(
    modifier: Modifier = Modifier,
    state: LaunchDetailsScreenState,
    resetErrorState: () -> Unit
) {
    ThermondoBackground(
        modifier = modifier
            .fillMaxSize()
            .padding(AppTheme.padding.l)
    ) {

        Box(Modifier.fillMaxSize()) {

            state.launch?.let {
                LaunchDetails(launch = it)
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

// add preview
@Composable
private fun LaunchDetails(
    modifier: Modifier = Modifier,
    launch: Launch
) {
    Column {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(AppTheme.padding.xl * 2),
            model = launch.links.patch.small, contentDescription = null
        )
        Spacer(modifier = modifier.height(AppTheme.padding.m))

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        Text(
            text = launch.dateLocal.toReadableDate(),
            style = MaterialTheme.typography.bodySmall,
        )

        Spacer(modifier = modifier.height(AppTheme.padding.m))


        Text(
            modifier = modifier.verticalScroll(state = rememberScrollState()),
            text = launch.details.toString(),
            style = MaterialTheme.typography.bodyMedium,
        )

    }
}

