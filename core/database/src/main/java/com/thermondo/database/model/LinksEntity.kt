package com.thermondo.database.model

import androidx.room.Embedded

data class LinksEntity(
    val article: String?,
    @Embedded
    val flickr: FlickrEntity,
    @Embedded
    val patch: PatchEntity,
    val presskit: String?,
    @Embedded
    val reddit: RedditEntity,
    val webcast: String?,
    val wikipedia: String?,
    val youtubeId: String?
)

