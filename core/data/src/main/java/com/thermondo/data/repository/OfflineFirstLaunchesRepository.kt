package com.thermondo.data.repository

import androidx.paging.PagingSource
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.IO
import com.thermondo.data.model.asEntity
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.LaunchEntity
import com.thermondo.network.SpacexNetworkDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class OfflineFirstLaunchesRepository @Inject constructor(
    private val remoteDataSource: SpacexNetworkDataSource,
    private val localDataSource: LaunchDao,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : LaunchesRepository {

    override suspend fun getLaunch(launchId: String): Flow<LaunchEntity> {
        return localDataSource.getLaunchEntity(launchId)
    }

    override suspend fun syncLaunches() {
        withContext(dispatcher) {
            val networkLaunches = remoteDataSource.getLaunches()
            localDataSource.upsertLaunches(networkLaunches.map { it.asEntity() })
        }
    }

    override fun getLaunchesPaged(): PagingSource<Int, LaunchEntity> {
        return localDataSource.getPagedLaunchEntities()
    }
}