package com.thermondo.common

import kotlinx.serialization.json.Json

val jsonSerializer = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}