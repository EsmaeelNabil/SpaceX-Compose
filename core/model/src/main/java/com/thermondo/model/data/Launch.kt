package com.thermondo.model.data

data class Launch(
    val autoUpdate: Boolean = false,
    val capsules: List<String> = listOf(),
    val cores: List<Core> = listOf(),
    val dateLocal: String = "",
    val datePrecision: String = "",
    val dateUnix: Int = 0,
    val dateUtc: String = "",
    val details: String = "",
    val flightNumber: Int = 0,
    val id: String = "",
    val launchpad: String = "",
    val links: Links = Links(),
    val name: String = "",
    val net: Boolean = false,
    val payloads: List<String> = listOf(),
    val rocket: String = "",
    val staticFireDateUnix: Int = 0,
    val staticFireDateUtc: String = "",
    val success: Boolean = false,
    val tdb: Boolean = false,
    val upcoming: Boolean = false,
    val window: Int = 0
)