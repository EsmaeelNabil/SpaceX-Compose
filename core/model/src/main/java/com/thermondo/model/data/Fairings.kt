package com.thermondo.model.data

data class Fairings(
    val recovered: Boolean? = null,
    val recoveryAttempt: Boolean? = null,
    val reused: Boolean? = null,
    val ships: List<String> = listOf()
)