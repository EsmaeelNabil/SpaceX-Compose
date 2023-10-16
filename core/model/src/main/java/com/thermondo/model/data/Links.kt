package com.thermondo.model.data


data class Links(
    val article: String? = null,
    val flickr: Flickr = Flickr(),
    val patch: Patch = Patch(),
    val presskit: String? = null,
    val reddit: Reddit = Reddit(),
    val webcast: String? = null,
    val wikipedia: String? = null,
    val youtubeId: String? = null
)