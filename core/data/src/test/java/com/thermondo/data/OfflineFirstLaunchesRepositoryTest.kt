package com.thermondo.data

import androidx.paging.PagingSource
import com.thermondo.common.Result
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.IO
import com.thermondo.data.model.asEntity
import com.thermondo.data.model.asExternalModel
import com.thermondo.data.repository.OfflineFirstLaunchesRepository
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.LaunchEntity
import com.thermondo.network.SpacexNetworkDataSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class OfflineFirstLaunchesRepositoryTest {

    private val remoteDataSource: SpacexNetworkDataSource = mockk()
    private val localDataSource: LaunchDao = mockk()
    private val mockedPagedLaunchesSource: PagingSource<Int, LaunchEntity> = mockk()
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var repo: OfflineFirstLaunchesRepository

    private val launchId = "1"

    @Test
    fun getLaunch_returns_flow_of_launch_entity_when_specified_with_id() = runTest {
        every { localDataSource.getLaunchEntity(launchId) } returns flowOf(
            fakeInMemoryEntityLaunches.first().copy(id = launchId)
        )

        repo = OfflineFirstLaunchesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = testDispatcher
        )

        val expectedResult = fakeInMemoryEntityLaunches.first()
        val result = repo.getLaunch(launchId).first()

        assertEquals(expectedResult, result)
    }

    @Test
    fun getCachedLaunchesCount_returns_count_of_launch_entities_when_database_is_not_empty() =
        runTest {
            val expectedCount = 100

            every { localDataSource.getLaunchesCount() } returns flowOf(expectedCount)

            repo = OfflineFirstLaunchesRepository(
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
                dispatcher = testDispatcher
            )

            val result = repo.getCachedLaunchesCount()

            assertEquals(expectedCount, result.first())
        }

    @Test
    fun getCachedLaunchesCount_returns_zero_when_database_is_empty() = runTest {
        val expectedCount = 0

        every { localDataSource.getLaunchesCount() } returns flowOf(expectedCount)

        repo = OfflineFirstLaunchesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = testDispatcher
        )

        val result = repo.getCachedLaunchesCount().first()

        assertEquals(expectedCount, result)
    }

    // we can cover the rest of the cases of this in integration test [ repo, remote, database]
    // such as data is saved correctly as we got it from the internet.
    @Test
    fun syncLaunches_finishes_without_any_exceptions() = runTest {
        try {
            coEvery { remoteDataSource.getLaunches() } returns fakeInMemoryNetworkLaunches

            every { localDataSource.upsertLaunches(fakeInMemoryEntityLaunches) } returns List(
                fakeInMemoryEntityLaunches.size
            ) { it.toLong() }


            repo = OfflineFirstLaunchesRepository(
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
                dispatcher = testDispatcher
            )

            repo.syncLaunches()
        } catch (e: Exception) {
            fail("An exception was thrown: ${e.message}")
        }
    }

    @Test
    fun getLaunches_finishes_with_an_io_exception_in_case_of_no_internet() = runTest {
        coEvery { remoteDataSource.getLaunches() } throws IOException("No Internet")

        repo = OfflineFirstLaunchesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = testDispatcher
        )

        val exception = repo.syncLaunches()

        assertTrue(exception is Result.Error)
        assertTrue((exception as Result.Error).exception is IOException)
    }

    @Test
    fun getLaunchesPaged_returns_the_mocked_paging_source_from_room() = runTest {

        every { localDataSource.getPagedLaunchEntities() } returns mockedPagedLaunchesSource

        repo = OfflineFirstLaunchesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = testDispatcher
        )

        val pagedLaunches = repo.getLaunchesPaged()

        assertEquals(mockedPagedLaunchesSource, pagedLaunches)
    }
}