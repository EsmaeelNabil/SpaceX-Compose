package com.thermondo.database.model

import androidx.room.ColumnInfo

data class PatchEntity(
    @ColumnInfo(name = "patch_large")
    val large: String?,
    @ColumnInfo(name = "patch_small")
    val small: String?
)