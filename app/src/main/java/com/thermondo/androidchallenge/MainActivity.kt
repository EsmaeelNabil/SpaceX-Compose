package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.designsystem.theme.ThermondoTheme
import com.thermondo.network.SpacexNetworkDataSource
import com.thermondo.network.ktor.KtorSpacexNetworkDataSource
import com.thermondo.network.model.NetworkLaunch
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var spacexNetworkDataSource: SpacexNetworkDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val ss = spacexNetworkDataSource.getLaunches()
            println(ss)
        }
        setContent {
            ThermondoTheme {
                ThermondoButton(onClick = { }) {
                    var list by remember {
                        mutableStateOf(listOf<NetworkLaunch>())
                    }

                    LaunchedEffect(Unit) {
                        list = spacexNetworkDataSource.getLaunches()
                    }

                    LazyColumn {
                        items(list) {
                            LaunchItem(it)
                        }
                    }

                    Text(text = "Design system")
                }
            }
        }
    }

    @Composable
    fun LaunchItem(launch: NetworkLaunch) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.padding.m)) {
            Column {
                Text(text = launch.id)
                Text(text = launch.name)
                Text(text = launch.details.toString())
                Text(text = launch.networkLinks.webcast.toString())
            }
        }
    }
}