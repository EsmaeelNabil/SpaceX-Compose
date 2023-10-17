package com.thermondo.bookmarks

import com.thermondo.model.data.Launch

data class BookmarksScreenState(
    val launches: List<Launch> = listOf(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val bookmarkedLaunchesIds: Set<String> = setOf()
)