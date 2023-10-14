package com.thermondo.model.data

data class Links(
    val article: String = "",
    val flickr: Flickr = Flickr(),
    val patch: Patch = Patch(),
    val presskit: String = "",
    val reddit: Reddit = Reddit(),
    val webcast: String = "",
    val wikipedia: String = "",
    val youtubeId: String = ""
)