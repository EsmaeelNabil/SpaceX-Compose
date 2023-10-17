package com.thermondo.launch_details

import com.thermondo.model.data.Launch

data class LaunchDetailsScreenState(
    val launch: Launch? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
)