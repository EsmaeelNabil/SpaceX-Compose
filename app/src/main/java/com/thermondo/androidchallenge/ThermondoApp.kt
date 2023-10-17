package com.thermondo.androidchallenge

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.compose.rememberNavController
import com.thermondo.androidchallenge.navigation.AppNavHost
import com.thermondo.bookmarks.navigation.navigateToBookmarks
import com.thermondo.designsystem.component.ThermondoBackground
import com.thermondo.designsystem.icons.ThermondoIcons
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.launches.navigation.navigateToLaunches
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThermondoApplication : Application()

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThermondoApp() {
    val navController = rememberNavController()

    ThermondoBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navController.navigateToLaunches()
                            }
                            .size(AppTheme.padding.xl),
                        imageVector = ThermondoIcons.Search, contentDescription = null,
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navController.navigateToBookmarks()
                            }
                            .size(AppTheme.padding.xl),
                        imageVector = ThermondoIcons.bookmarks, contentDescription = null
                    )
                }
            }
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
                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun BottomNavBar() {

}
