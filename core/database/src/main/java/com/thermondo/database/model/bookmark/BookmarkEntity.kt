package com.thermondo.database.model.bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(@PrimaryKey val launchId: String)