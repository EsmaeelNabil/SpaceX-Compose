package com.thermondo.network

import com.thermondo.network.model.NetworkLaunch

interface SpacexNetworkDataSource {
    suspend fun getLaunches(): List<NetworkLaunch>
}

