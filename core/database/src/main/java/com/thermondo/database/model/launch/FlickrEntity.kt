package com.thermondo.database.model.launch

import androidx.room.ColumnInfo

data class FlickrEntity(
    val original: List<String>,
    @ColumnInfo(name = "flicker_small")
    val small: List<String>
)