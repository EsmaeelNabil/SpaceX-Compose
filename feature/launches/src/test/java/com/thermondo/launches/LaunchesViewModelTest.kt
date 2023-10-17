package com.thermondo.launches

import androidx.paging.*
import app.cash.turbine.test
import com.thermondo.common.Result
import com.thermondo.domain.usecases.GetCachedLaunchesSizeUseCase
import com.thermondo.domain.usecases.GetPagedLaunchesUseCase
import com.thermondo.domain.usecases.SyncLaunchesUseCase
import com.thermondo.model.data.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LaunchesViewModelTest {

    private val syncLaunchesUseCase: SyncLaunchesUseCase = mockk()
    private val getPagedLaunchesUseCase: GetPagedLaunchesUseCase = mockk()
    private val getCachedLaunchesSizeUseCase: GetCachedLaunchesSizeUseCase = mockk()
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var viewModel: LaunchesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { getCachedLaunchesSizeUseCase() } returns flowOf(0)
        viewModel = LaunchesViewModel(
            syncLaunchesUseCase,
            getPagedLaunchesUseCase,
            getCachedLaunchesSizeUseCase,
            testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is initialized, syncLaunches is called`() = runTest {
        coVerify { syncLaunchesUseCase() }
    }

    @Test
    fun `resetErrorState sets error to null`() = runTest {
        viewModel.resetErrorState()
        val state = viewModel.state.first()
        assert(state.error == null)
    }

    @Test
    fun `syncLaunches updates state on Result Error`() = runTest {
        val expectedErrorMessage = "Network error"
        coEvery { syncLaunchesUseCase() } returns
                Result.Error(Exception(expectedErrorMessage))

        viewModel.syncLaunches()

        viewModel.state.test {
            assertEquals(awaitItem().error, expectedErrorMessage)
        }
    }

    @Test
    fun `syncLaunches updates state on Result Loading`() = runTest {
        coEvery { syncLaunchesUseCase() } returns Result.Loading

        viewModel.syncLaunches()

        viewModel.state.test {
            assert(awaitItem().isLoading)
        }
    }

    @Test
    fun `syncLaunches updates state on Result Success`() = runTest {
        coEvery { syncLaunchesUseCase() } returns Result.Success(listOf())

        viewModel.syncLaunches()

        viewModel.state.test {
            val item = awaitItem()
            assert(!item.isLoading && item.isRefreshPagingRequired)
        }
    }

    @Test
    fun `cacheSize is initialized correctly`() = runTest {
        val flow = getCachedLaunchesSizeUseCase()
        flow.test {
            assert(awaitItem() == 0)
            awaitComplete()
        }
    }

    @Test
    fun `getPagedLaunches returns expected PagingData`() = runTest {

        val expectedPagingData = PagingData.from(
            listOf(
                Launch(id = "randomId0"),
                Launch(id = "randomId1"),
                Launch(id = "randomId2"),
            )
        )

        every { getPagedLaunchesUseCase() } returns flowOf(expectedPagingData)

        viewModel.getPagedLaunches().test {
            assertEquals(
                expectedPagingData.collectData().size,
                awaitItem().collectData().size
            )
        }
    }
}

