package com.thermondo.database.model

import kotlinx.serialization.Serializable

@Serializable
data class CoreEntity(
    val core: String?,
    val flight: Int?,
    val gridfins: Boolean?,
    val landingAttempt: Boolean?,
    val landingSuccess: Boolean?,
    val landingType: String?,
    val landpad: String?,
    val legs: Boolean?,
    val reused: Boolean?
)