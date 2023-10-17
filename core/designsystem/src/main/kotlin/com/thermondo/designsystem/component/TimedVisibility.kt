package com.thermondo.designsystem.component

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

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