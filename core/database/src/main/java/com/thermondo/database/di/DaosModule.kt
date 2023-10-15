package com.thermondo.database.di

import com.thermondo.database.ThermondoDatabase
import com.thermondo.database.dao.LaunchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesLaunchesDao(
        database: ThermondoDatabase,
    ): LaunchDao = database.launchesDao()
}

