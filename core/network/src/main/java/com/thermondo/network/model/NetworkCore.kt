package com.thermondo.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCore(
    val core: String? = null,
    val flight: Int? = null,
    val gridfins: Boolean? = null,
    @SerialName("landing_attempt")
    val landingAttempt: Boolean? = null,
    @SerialName("landing_success")
    val landingSuccess: Boolean? = null,
    @SerialName("landing_type")
    val landingType: String? = null,
    val landpad: String? = null,
    val legs: Boolean? = null,
    val reused: Boolean? = null
)