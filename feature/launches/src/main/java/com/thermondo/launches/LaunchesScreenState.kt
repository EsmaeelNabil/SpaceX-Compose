package com.thermondo.launches

data class LaunchesScreenState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val isRefreshPagingRequired: Boolean = false,
    val bookmarkedLaunchesIds: Set<String> = setOf()
)