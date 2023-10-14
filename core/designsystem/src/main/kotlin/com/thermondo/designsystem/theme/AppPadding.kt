package com.thermondo.designsystem.theme

import android.annotation.SuppressLint
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Class that holds different common paddings
 *
 * @property none
 * @property xxs
 * @property xs
 * @property s
 * @property sm
 * @property m
 * @property ml
 * @property l
 * @property xl
 */
@Immutable
data class AppPadding(
    val none: Dp = 0.dp,
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val s: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val m: Dp = 16.dp,
    val ml: Dp = 20.dp,
    val l: Dp = 32.dp,
    val xl: Dp = 64.dp,
)

@SuppressLint("ComposeCompositionLocalUsage")
internal val LocalAppPadding = staticCompositionLocalOf { AppPadding() }
