package com.thermondo.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkCrew(
    val crew: String = "",
    val role: String = ""
)