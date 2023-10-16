package com.thermondo.data.repository

import androidx.paging.PagingSource
import com.thermondo.database.model.LaunchEntity
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    suspend fun getLaunch(launchId: String): Flow<LaunchEntity>
    suspend fun syncLaunches()
    fun getLaunchesPaged(): PagingSource<Int, LaunchEntity>
}