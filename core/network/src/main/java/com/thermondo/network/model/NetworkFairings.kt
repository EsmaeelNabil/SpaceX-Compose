package com.thermondo.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkFairings(
    val recovered: Boolean? = null,
    @SerialName("recovery_attempt")
    val recoveryAttempt: Boolean? = null,
    val reused: Boolean? = null,
    val ships: List<String> = listOf()
)