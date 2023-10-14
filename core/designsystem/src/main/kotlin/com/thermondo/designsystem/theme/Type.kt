package com.thermondo.designsystem.theme

import android.annotation.SuppressLint
import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thermondo.designsystem.R

private val roboto = FontFamily(
    Font(
        resId = R.font.roboto_regular,
        style = FontStyle.Normal,
    ),
)

@Suppress("LongMethod")
internal fun thermondoTypography() = Typography(
    displayLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.W400,
    ),
    displaySmall = TextStyle(
        fontFamily = roboto,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.W400,
    ),
    headlineLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.W400,
    ),
    headlineMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 28.sp,
        lineHeight = 436.sp,
        fontWeight = FontWeight.W400,
    ),
    headlineSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.W400,
    ),
    titleLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W700,
    ),
    titleMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = 0.15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = roboto,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.5.sp,
    ),
)

@SuppressLint("ComposeCompositionLocalUsage")
internal val LocalAppTypography = staticCompositionLocalOf { thermondoTypography() }
