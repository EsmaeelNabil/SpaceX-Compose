package com.thermondo.database.dao

import androidx.room.*
import com.thermondo.database.model.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query(
        value = """
        SELECT * FROM launches
        WHERE id = :launchId
        """
    )
    fun getLaunchEntity(launchId: String): Flow<LaunchEntity>

    @Query(value = "SELECT * FROM launches")
    fun getLaunchEntities(): Flow<List<LaunchEntity>>

    /**
     * fetches [launchEntities] from the db if their id is one of the given id's
     * will be used to fetch bookmarked launches.
     */
    @Query(
        value = """ 
        SELECT * FROM launches
        WHERE id IN (:ids)
        """
    )
    fun getLaunchEntities(ids: Set<String>): Flow<List<LaunchEntity>>

    /**
     * Inserts [launchEntities] into the db if they don't exist, and replace those that do
     */
    @Upsert
    fun upsertLaunches(launchEntities: List<LaunchEntity>): List<Long>
}