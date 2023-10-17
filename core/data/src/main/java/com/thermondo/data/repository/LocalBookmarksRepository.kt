package com.thermondo.data.repository

import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.*
import com.thermondo.database.dao.BookmarkDao
import com.thermondo.database.model.bookmark.BookmarkEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * create an actual [localDataSource] to separate the Dao from the logic in the future
 */
class LocalBookmarksRepository @Inject constructor(
    private val localDataSource: BookmarkDao,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : BookmarksRepository {

    override suspend fun addBookmark(launchId: String) = withContext(dispatcher) {
        localDataSource.addBookmark(BookmarkEntity(launchId))
    }

    override suspend fun removeBookmark(launchId: String) = withContext(dispatcher) {
        localDataSource.removeBookmark(BookmarkEntity(launchId))
    }

    override suspend fun isBookmarked(launchId: String): Boolean {
        return localDataSource.exists(launchId)
    }

    override fun getAllBookmarksIds() = localDataSource.getAllBookmarkedIds()
}