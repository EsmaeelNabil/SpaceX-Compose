package com.thermondo.database.model.launch

import kotlinx.serialization.Serializable

@Serializable
data class CrewEntity(
    val crew: String,
    val role: String
)