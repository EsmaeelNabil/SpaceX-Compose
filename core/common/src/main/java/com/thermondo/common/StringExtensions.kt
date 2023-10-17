package com.thermondo.common

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.toReadableDate(): String {
    return try {
        Instant.parse(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date.toString()
    } catch (e: Exception) {
        this
    }
}