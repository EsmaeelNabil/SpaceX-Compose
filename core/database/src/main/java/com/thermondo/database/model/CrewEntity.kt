package com.thermondo.database.model

import kotlinx.serialization.Serializable

@Serializable
data class CrewEntity(
    val crew: String,
    val role: String
)