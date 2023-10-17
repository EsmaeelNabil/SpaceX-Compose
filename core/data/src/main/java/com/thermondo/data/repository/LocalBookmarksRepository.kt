package com.thermondo.data.repository

import com.thermondo.common.di.Dispatcher
import com.thermondo.common.di.ThermondoDispatchers.*
import com.thermondo.common.exists
import com.thermondo.common.isAddedSuccessfully
import com.thermondo.common.isRemovedSuccessfully
import com.thermondo.data.model.asBookmark
import com.thermondo.database.dao.BookmarkDao
import com.thermondo.database.model.bookmark.BookmarkEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
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
        localDataSource.addBookmark(BookmarkEntity(launchId)).isAddedSuccessfully()
    }

    override suspend fun removeBookmark(launchId: String) = withContext(dispatcher) {
        localDataSource.removeBookmark(BookmarkEntity(launchId)).isRemovedSuccessfully()
    }

    override suspend fun isBookmarked(launchId: String): Boolean {
        return localDataSource.getAllBookmarkedIds().first()
            .find { it.launchId == launchId } != null
    }

    override fun getAllBookmarksIds() = localDataSource
        .getAllBookmarkedIds()
        .map { it.map { it.asBookmark() } }
}