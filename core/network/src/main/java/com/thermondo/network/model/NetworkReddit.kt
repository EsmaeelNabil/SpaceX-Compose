package com.thermondo.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkReddit(
    val campaign: String? = null,
    val launch: String? = null,
    val media: String? = null,
    val recovery: String? = null
)