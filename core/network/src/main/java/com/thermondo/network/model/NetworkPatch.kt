package com.thermondo.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkPatch(
    val large: String? = null,
    val small: String? = null
)