package com.thermondo.database.dao

import androidx.room.*
import com.thermondo.database.model.bookmark.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun removeBookmark(bookmark: BookmarkEntity)

    @Query("SELECT launchId FROM bookmarks")
    fun getAllBookmarkedIds(): Flow<List<String>>
}