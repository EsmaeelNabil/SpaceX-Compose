package com.thermondo.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkFailure(
    val altitude: Int? = null,
    val reason: String = "",
    val time: Int = 0
)