package com.thermondo.androidchallenge

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thermondo.androidchallenge.navigation.AppNavHost
import com.thermondo.designsystem.component.ThermondoBackground
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThermondoApplication : Application()

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThermondoApp() {
    ThermondoBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Vertical,
                        ),
                    ),
            ) {

                AppNavHost()

            }
        }
    }
}
