package com.thermondo.data.di

import com.thermondo.data.repository.BookmarksRepository
import com.thermondo.data.repository.LaunchesRepository
import com.thermondo.data.repository.LocalBookmarksRepository
import com.thermondo.data.repository.OfflineFirstLaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsLaunchesRepository(
        launchesRepository: OfflineFirstLaunchesRepository
    ): LaunchesRepository

    @Binds
    fun bindsBookmarksRepository(
        launchesRepository: LocalBookmarksRepository
    ): BookmarksRepository
}