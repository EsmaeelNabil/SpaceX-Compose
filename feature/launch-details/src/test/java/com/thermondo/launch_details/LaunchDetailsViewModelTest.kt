package com.thermondo.launch_details

import app.cash.turbine.test
import com.thermondo.common.Result
import com.thermondo.data.repository.LaunchesRepository
import com.thermondo.model.data.Launch
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LaunchDetailsViewModelTest {

    private val launchesRepository: LaunchesRepository = mockk()
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var viewModel: LaunchDetailsViewModel

    @Before
    fun setup() {
        viewModel = LaunchDetailsViewModel(launchesRepository, dispatcher)
    }

    @Test
    fun `test getLaunchDetails with Success`() = runTest {
        val launchId = "1"
        val mockLaunch = Launch(id = launchId)

        coEvery { launchesRepository.getLaunch(launchId) } returns Result.Success(mockLaunch)

        viewModel.getLaunchDetails(launchId)

        viewModel.state.test {
            assertEquals(
                LaunchDetailsScreenState(isLoading = false, launch = mockLaunch), awaitItem()
            )
        }
    }

    @Test
    fun `test getLaunchDetails with Error`() = runTest {
        coEvery { launchesRepository.getLaunch("id") } returns Result.Error(Exception("An error"))

        viewModel.getLaunchDetails("id")

        viewModel.state.test {
            assertEquals(
                LaunchDetailsScreenState(isLoading = false, error = "An error"), awaitItem())
        }
    }

    @Test
    fun `test getLaunchDetails with Loading`() = runTest {
        coEvery { launchesRepository.getLaunch("id") } returns Result.Loading

        viewModel.getLaunchDetails("id")

        viewModel.state.test {
            assertEquals(LaunchDetailsScreenState(isLoading = true), awaitItem())
        }
    }

    @Test
    fun `test resetErrorState`() = runTest {
        viewModel.resetErrorState()

        viewModel.state.test {
            assertEquals(LaunchDetailsScreenState(error = null), awaitItem())
        }
    }
}