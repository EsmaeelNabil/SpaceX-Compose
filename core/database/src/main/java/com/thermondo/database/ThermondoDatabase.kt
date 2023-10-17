package com.thermondo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thermondo.database.dao.BookmarkDao
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.bookmark.BookmarkEntity
import com.thermondo.database.model.launch.LaunchEntity
import com.thermondo.database.util.*

@Database(
    entities = [LaunchEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    StringListConverter::class,
    CoreEntityConverter::class,
    CrewEntityConverter::class,
    FailureEntityConverter::class
)
abstract class ThermondoDatabase : RoomDatabase() {
    abstract fun launchesDao(): LaunchDao
    abstract fun bookmarksDao(): BookmarkDao
}