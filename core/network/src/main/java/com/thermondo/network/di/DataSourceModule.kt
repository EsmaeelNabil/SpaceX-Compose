package com.thermondo.network.di

import com.thermondo.network.SpacexNetworkDataSource
import com.thermondo.network.ktor.KtorSpacexNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideKtorSpaceXDataSource(httpClient: HttpClient): SpacexNetworkDataSource {
        return KtorSpacexNetworkDataSource(httpClient)
    }
}