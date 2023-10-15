package com.thermondo.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLaunch(
    @SerialName("auto_update")
    val autoUpdate: Boolean = false,
    val capsules: List<String> = listOf(),
    val networkCores: List<NetworkCore> = listOf(),
    val networkCrew: List<NetworkCrew> = listOf(),
    @SerialName("date_local")
    val dateLocal: String = "",
    @SerialName("date_precision")
    val datePrecision: String = "",
    @SerialName("date_unix")
    val dateUnix: Int = 0,
    @SerialName("date_utc")
    val dateUtc: String = "",
    val details: String? = null,
    val networkFailures: List<NetworkFailure> = listOf(),
    val networkFairings: NetworkFairings? = null,
    @SerialName("flight_number")
    val flightNumber: Int = 0,
    val id: String = "",
    @SerialName("launch_library_id")
    val launchLibraryId: String? = null,
    val launchpad: String = "",
    val networkLinks: NetworkLinks = NetworkLinks(),
    val name: String = "",
    val net: Boolean = false,
    val payloads: List<String> = listOf(),
    val rocket: String = "",
    val ships: List<String> = listOf(),
    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Int? = null,
    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,
    val success: Boolean? = null,
    val tbd: Boolean = false,
    val upcoming: Boolean = false,
    val window: Int? = null
)