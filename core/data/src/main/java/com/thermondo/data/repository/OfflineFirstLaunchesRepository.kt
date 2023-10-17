package com.thermondo.data.repository

import androidx.paging.PagingSource
import com.thermondo.common.Result
import com.thermondo.common.ThermondoException
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.IO
import com.thermondo.data.model.asEntity
import com.thermondo.data.model.asExternalModel
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.LaunchEntity
import com.thermondo.network.SpacexNetworkDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class OfflineFirstLaunchesRepository @Inject constructor(
    private val remoteDataSource: SpacexNetworkDataSource,
    private val localDataSource: LaunchDao,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : LaunchesRepository {

    override suspend fun getLaunch(launchId: String) = withContext(dispatcher) {
        try {
            return@withContext Result.Success(
                localDataSource.getLaunchEntity(launchId).asExternalModel()
            )
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override fun getCachedLaunchesCount(): Flow<Int> {
        return localDataSource.getLaunchesCount()
    }

    /**
     * for simplicity, and for that the data won't change often.
     * would be good to separate them or use RemoteMediator in case the data changes often.
     */
    override suspend fun syncLaunches() = withContext(dispatcher) {
        try {
            val networkLaunches = remoteDataSource.getLaunches()
            localDataSource.upsertLaunches(networkLaunches.map { it.asEntity() })
            return@withContext Result.Success(networkLaunches)
        } catch (io: IOException) {
            return@withContext Result.Error(io)
        } catch (cancellation: CancellationException) {
            return@withContext Result.Error(ThermondoException())
        }
    }

    override fun getLaunchesPaged(): PagingSource<Int, LaunchEntity> {
        return localDataSource.getPagedLaunchEntities()
    }
}