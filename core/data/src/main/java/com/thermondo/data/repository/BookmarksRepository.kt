package com.thermondo.data.repository

import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    suspend fun addBookmark(launchId: String)
    suspend fun removeBookmark(launchId: String)
    fun getAllBookmarksIds(): Flow<List<String>>
}