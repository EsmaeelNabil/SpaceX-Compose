package com.thermondo.model.data

data class Core(
    val core: String? = null,
    val flight: Int? = null,
    val gridfins: Boolean? = null,
    val landingAttempt: Boolean? = null,
    val landingSuccess: Boolean? = null,
    val landingType: String? = null,
    val landpad: String? = null,
    val legs: Boolean? = null,
    val reused: Boolean? = null
)