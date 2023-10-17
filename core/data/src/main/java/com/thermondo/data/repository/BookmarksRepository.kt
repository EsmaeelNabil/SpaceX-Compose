package com.thermondo.data.repository

import com.thermondo.database.model.bookmark.BookmarkEntity
import com.thermondo.model.data.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    suspend fun addBookmark(launchId: String) : Boolean
    suspend fun removeBookmark(launchId: String) : Boolean
    suspend fun isBookmarked(launchId: String): Boolean
    fun getAllBookmarksIds(): Flow<List<Bookmark>>
}