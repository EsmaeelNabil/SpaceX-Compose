package com.thermondo.network.ktor

import com.thermondo.network.SpacexNetworkDataSource
import com.thermondo.network.model.NetworkLaunch
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorSpacexNetworkDataSource @Inject constructor(
    private val httpClient: HttpClient
) : SpacexNetworkDataSource {

    override suspend fun getLaunches(): List<NetworkLaunch> {
        return httpClient.use {
            it.get(LAUNCHES_ENDPOINT).body()
        }
    }

    companion object {
        const val LAUNCHES_ENDPOINT = "/v5/launches"
    }
}