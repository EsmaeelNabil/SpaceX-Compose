package com.thermondo.database.di

import android.content.Context
import androidx.room.Room
import com.thermondo.database.ThermondoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): ThermondoDatabase = Room.databaseBuilder(
        context,
        ThermondoDatabase::class.java,
        "thermondo-database",
    ).build()
}