package com.thermondo.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkFlickr(
    val original: List<String> = listOf(),
    val small: List<String> = listOf()
)