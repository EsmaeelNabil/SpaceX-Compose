package com.thermondo.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class BookmarksViewModel @Inject constructor(
    private val getBookmarkedLaunchesUseCase: GetBookmarkedLaunchesUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    @Dispatcher(Main) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarksScreenState())
    val state = _state.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _state.update {
            it.copy(error = throwable.localizedMessage)
        }
    }

    init {
        getBookmarks()
        getBookmarksIds()
    }

    /**
     * keeps track of the bookmarked Launches, gets updated live from Room
     */
    private fun getBookmarksIds() = viewModelScope.launch(errorHandler + dispatcher) {
        getBookmarksUseCase().collect { entities ->
            _state.update { state ->
                state.copy(
                    bookmarkedLaunchesIds = entities.map { it.launchId }.toSet()
                )
            }
        }
    }

    fun resetErrorState() = _state.update { it.copy(error = null) }

    fun removeBookmark(launchId: String) = viewModelScope.launch(errorHandler + dispatcher) {
        if (removeBookmarkUseCase(launchId)) {
            removeBookmarkFromState(launchId)
        }
    }

    private fun removeBookmarkFromState(launchId: String) {
        _state.update { state ->
            state.copy(
                launches =
                state.launches.filterNot { it.id == launchId }
            )
        }
    }

    fun getBookmarks() = viewModelScope.launch(errorHandler + dispatcher) {
        getBookmarkedLaunchesUseCase()
            .onStart {
                _state.update { it.copy(isLoading = true) }
            }
            .catch { t ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = t.localizedMessage
                    )
                }
            }
            .collect { results ->
                _state.update {
                    it.copy(
                        launches = results,
                        isLoading = false
                    )
                }
            }
    }
}