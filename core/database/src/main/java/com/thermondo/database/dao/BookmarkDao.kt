package com.thermondo.database.dao

import androidx.room.*
import com.thermondo.database.model.bookmark.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(bookmark: BookmarkEntity) : Long

    @Delete
    fun removeBookmark(bookmark: BookmarkEntity) : Int

    @Query("SELECT launchId FROM bookmarks")
    fun getAllBookmarkedIds(): Flow<List<BookmarkEntity>>

}