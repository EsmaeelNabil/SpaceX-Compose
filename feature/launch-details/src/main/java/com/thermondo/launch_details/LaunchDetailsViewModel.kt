package com.thermondo.launch_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thermondo.common.Result
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.Main
import com.thermondo.data.repository.LaunchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    @Dispatcher(Main) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchDetailsScreenState())
    val state = _state.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _state.update {
            it.copy(error = throwable.localizedMessage)
        }
    }

    fun getLaunchDetails(launchId: String) =
        viewModelScope.launch(errorHandler + dispatcher) {
            when (val launchResult = launchesRepository.getLaunch(launchId)) {
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false, error = launchResult.exception?.localizedMessage
                    )
                }

                Result.Loading -> _state.update { it.copy(isLoading = true) }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        launch = launchResult.data
                    )
                }
            }
        }

    fun resetErrorState() = _state.update { it.copy(error = null) }
}