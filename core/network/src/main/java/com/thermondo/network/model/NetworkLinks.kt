package com.thermondo.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLinks(
    val article: String? = null,
    val flickr: NetworkFlickr = NetworkFlickr(),
    val patch: NetworkPatch = NetworkPatch(),
    val presskit: String? = null,
    val reddit: NetworkReddit = NetworkReddit(),
    val webcast: String? = null,
    val wikipedia: String? = null,
    @SerialName("youtube_id")
    val youtubeId: String? = null
)