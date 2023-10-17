package com.thermondo.launches

data class LaunchesScreenState(
    val cachedCount: Int = 0,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isRefreshPagingRequired: Boolean = false
)