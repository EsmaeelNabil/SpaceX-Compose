package com.thermondo.data.repository

import androidx.paging.PagingSource
import com.thermondo.common.Result
import com.thermondo.database.model.launch.LaunchEntity
import com.thermondo.model.data.Launch
import com.thermondo.network.model.NetworkLaunch
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    suspend fun getLaunch(launchId: String): Result<Launch>
    fun getCachedLaunchesCount(): Flow<Int>
    suspend fun syncLaunches() : Result<List<NetworkLaunch>>
    fun getLaunchesPaged(): PagingSource<Int, LaunchEntity>

    fun getLaunchesByIds(ids: Set<String>): Flow<List<LaunchEntity>>
}