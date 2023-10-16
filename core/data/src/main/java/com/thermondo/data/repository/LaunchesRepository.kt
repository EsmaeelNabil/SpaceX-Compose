package com.thermondo.data.repository

import androidx.paging.PagingSource
import com.thermondo.common.Result
import com.thermondo.database.model.LaunchEntity
import com.thermondo.network.model.NetworkLaunch
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    suspend fun getLaunch(launchId: String): Flow<LaunchEntity>
    fun getCachedLaunchesCount(): Flow<Int>
    suspend fun syncLaunches() : Result<List<NetworkLaunch>>
    fun getLaunchesPaged(): PagingSource<Int, LaunchEntity>
}