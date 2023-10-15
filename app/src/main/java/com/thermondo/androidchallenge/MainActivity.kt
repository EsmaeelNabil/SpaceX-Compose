package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.thermondo.data.model.asEntity
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.LaunchEntity
import com.thermondo.designsystem.component.ThermondoButton
import com.thermondo.designsystem.theme.AppTheme
import com.thermondo.designsystem.theme.ThermondoTheme
import com.thermondo.network.SpacexNetworkDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var spacexNetworkDataSource: SpacexNetworkDataSource

    @Inject
    lateinit var launchesDao: LaunchDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThermondoTheme {
                ThermondoButton(onClick = { }) {
                    var list by remember {
                        mutableStateOf(listOf<LaunchEntity>())
                    }

                    LaunchedEffect(Unit) {
                        val listt = spacexNetworkDataSource.getLaunches()
                        withContext(Dispatchers.IO){
                            launchesDao.upsertLaunches(listt.map {
                                it.asEntity()
                            })
                        }

                        launchesDao.getLaunchEntities().collect {
                            list = it
                        }
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
    fun LaunchItem(launch: LaunchEntity) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.padding.m)
        ) {
            Column {
                Text(text = launch.id)
                Text(text = launch.name)
                Text(text = launch.details.toString())
                Text(text = launch.links.webcast.toString())
            }
        }
    }
}