package com.thermondo.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.thermondo.database.dao.LaunchDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LaunchDaoTest {

    private lateinit var launchDao: LaunchDao
    private lateinit var db: ThermondoDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            ThermondoDatabase::class.java,
        ).build()

        launchDao = db.launchesDao()
    }

    @Test
    fun launchDao_fetches_launch_by_id() = runTest {

        launchDao.upsertLaunches(fakeInMemoryLaunches)

        val savedLaunch = launchDao.getLaunchEntity(fakeInMemoryLaunches.first().id)
        val expectedId = fakeInMemoryLaunches.first().id


        assertEquals(
            expectedId,
            savedLaunch.id
        )

    }

    @Test
    fun launchDao_getLaunchesCount_returns_count_with_data_in_database() = runTest {

        launchDao.upsertLaunches(fakeInMemoryLaunches)

        val savedLaunch = launchDao.getLaunchesCount().first()
        val expectedId = fakeInMemoryLaunches.size

        assertEquals(
            expectedId,
            savedLaunch
        )
    }

    @Test
    fun launchDao_getLaunchesCount_returns_zero_with_empty_database() = runTest {

        launchDao.upsertLaunches(listOf())

        val savedLaunch = launchDao.getLaunchesCount().first()
        val expectedId = 0

        assertEquals(
            expectedId,
            savedLaunch
        )
    }

    @Test
    fun launchDao_fetches_all_launch_entities() = runTest {

        launchDao.upsertLaunches(fakeInMemoryLaunches)

        val savedLaunch = launchDao.getLaunchEntitiesByIds()

        savedLaunch.test {
            val launches = awaitItem()
            assertEquals(fakeInMemoryLaunches.size, launches.size)
            assertEquals(fakeInMemoryLaunches.first().id, launches.first().id)
            assertEquals(fakeInMemoryLaunches.last().id, launches.last().id)
        }

    }

    @Test
    fun launchDao_upsertLaunches_insertLaunches_and_ignore_existing_ones() {

        val resultOne = launchDao.upsertLaunches(fakeInMemoryLaunches)

        val newItemId = "9"
        val newItemIndex = fakeInMemoryLaunches.lastIndex + 1

        val newLaunches = fakeInMemoryLaunches.toMutableList().apply {
            add(newItemIndex, last().copy(id = newItemId))
        }

        val resultTwo = launchDao.upsertLaunches(newLaunches)

        // the amount of insertions happened in the database = the size of items added
        // only in this case as it's empty.
        assertEquals(resultOne.size, fakeInMemoryLaunches.size)

        // only one insert operation happened --> for the newly added item
        // meaning the result will be [ -1,-1,-1,-1,-1,-1,-1, 9 ]
        // 9 : is the id of what got updated
        assertEquals(1, resultTwo.filter { it > 0 }.size)
    }
}