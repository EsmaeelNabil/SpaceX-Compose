package com.thermondo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thermondo.database.dao.LaunchDao
import com.thermondo.database.model.LaunchEntity
import com.thermondo.database.util.*

@Database(
    entities = [LaunchEntity::class],
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
}