package com.thermondo.model.data

data class Core(
    val core: String = "",
    val flight: Int = 0,
    val gridfins: Boolean = false,
    val landingAttempt: Boolean = false,
    val landingSuccess: Boolean = false,
    val landingType: String = "",
    val landpad: String = "",
    val legs: Boolean = false,
    val reused: Boolean = false
)