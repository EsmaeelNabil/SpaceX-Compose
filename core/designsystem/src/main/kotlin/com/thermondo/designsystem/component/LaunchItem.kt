package com.thermondo.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thermondo.common.toReadableDate
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.model.data.Launch

@Composable
fun LaunchItem(
    modifier: Modifier = Modifier,
    launch: Launch,
    // find another solution for this
    getIsBookmarked: (String) -> Boolean,
    onLaunchClick: (String) -> Unit,
    onBookmark: (Launch) -> Unit,
    onRemoveBookmark: (Launch) -> Unit
) {
    val isBookmarked by remember { mutableStateOf(getIsBookmarked(launch.id)) }

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

                Button(onClick = {
                    if (isBookmarked)
                        onRemoveBookmark(launch)
                    else onBookmark(launch)
                }) {
                    Text(if (isBookmarked) "Unbookmark" else "Bookmark")
                }
            }
        }
    }
}