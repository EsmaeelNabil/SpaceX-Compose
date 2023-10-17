package com.thermondo.androidchallenge.navigation

import com.thermondo.androidchallenge.R

enum class TopLevelDestination(
    val titleTextId: Int,
) {
    LAUNCHES(titleTextId = R.string.launches),
    BOOKMARKS(titleTextId = R.string.bookmarks),
    DETAILS(titleTextId = R.string.details),
}
