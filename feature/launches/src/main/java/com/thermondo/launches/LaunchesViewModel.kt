package com.thermondo.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.thermondo.common.Result
import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.Main
import com.thermondo.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val syncLaunchesUseCase: SyncLaunchesUseCase,
    private val getPagedLaunchesUseCase: GetPagedLaunchesUseCase,
    getCachedLaunchesSizeUseCase: GetCachedLaunchesSizeUseCase,
    private val addToBookmarksUseCase: AddToBookmarksUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    @Dispatcher(Main) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchesScreenState())
    val state = _state.asStateFlow()

    val cacheSize = getCachedLaunchesSizeUseCase().stateIn(
        viewModelScope, started = SharingStarted.WhileSubscribed(), 0
    )

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _state.update {
            it.copy(error = throwable.localizedMessage)
        }
    }

    init {
        syncLaunches()
        getBookmarks()
    }

    /**
     * keeps track of the bookmarked Launches, gets updated live from Room
     */
    private fun getBookmarks() = viewModelScope.launch(errorHandler + dispatcher) {
        getBookmarksUseCase().collect { entities ->
            _state.update { state ->
                state.copy(
                    bookmarkedLaunchesIds = entities.map { it.launchId }.toSet()
                )
            }
        }
    }

    fun resetErrorState() = _state.update { it.copy(error = null) }
    fun resetRefreshPagingState() = _state.update { it.copy(error = null) }

    fun syncLaunches() = viewModelScope.launch(errorHandler + dispatcher) {
        when (val syncResult = syncLaunchesUseCase()) {
            is Result.Error -> _state.update {
                it.copy(error = syncResult.exception?.localizedMessage, isLoading = false)
            }

            Result.Loading -> _state.update { it.copy(isLoading = true) }

            is Result.Success -> _state.update {
                it.copy(isLoading = false, isRefreshPagingRequired = true)
            }
        }

    }

    fun removeBookmark(launchId: String) = viewModelScope.launch(errorHandler + dispatcher) {
        removeBookmarkUseCase(launchId)
    }

    fun addBookmark(launchId: String) = viewModelScope.launch(errorHandler + dispatcher) {
        addToBookmarksUseCase(launchId)
    }

    fun getPagedLaunches() = getPagedLaunchesUseCase().cachedIn(viewModelScope)
}