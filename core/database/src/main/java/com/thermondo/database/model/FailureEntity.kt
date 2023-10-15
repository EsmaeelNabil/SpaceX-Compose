package com.thermondo.database.model

import kotlinx.serialization.Serializable

@Serializable
data class FailureEntity(
    val altitude: Int?,
    val reason: String,
    val time: Int
)