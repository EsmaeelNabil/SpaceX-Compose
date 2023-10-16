package com.thermondo.network

import com.thermondo.common.jsonSerializer
import com.thermondo.network.ktor.KtorSpacexNetworkDataSource
import com.thermondo.network.model.NetworkLaunch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class KtorSpacexNetworkDataSourceTest {

    private lateinit var ktorSpacexNetworkDataSource: KtorSpacexNetworkDataSource

    @Test
    fun ktorSpacexNetworkDataSource_getLaunches_returns_a_list_of_data() = runTest {

        // given
        val client = createMockClientWith(jsonResponse = fakeLaunchJsonResponse)
        ktorSpacexNetworkDataSource = KtorSpacexNetworkDataSource(client)

        //expected
        val expected = jsonSerializer.decodeFromString<List<NetworkLaunch>>(fakeLaunchJsonResponse)

        // assertions
        val result = ktorSpacexNetworkDataSource.getLaunches()
        assertEquals(expected.first().id, result.first().id)
        assertEquals(expected.last().id, result.last().id)
        assertEquals(expected.size, result.size)

    }
}

