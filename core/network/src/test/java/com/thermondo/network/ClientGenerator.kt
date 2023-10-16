package com.thermondo.network

import com.thermondo.common.jsonSerializer
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*

/**
 * Used for Unit-testing internet calls
 * creates an HttpClient with a MockEngine that gives a response with the specified [jsonResponse] string
 * and the given [statusCode] & [headers]
 */
fun createMockClientWith(
    jsonResponse: String = "",
    statusCode: HttpStatusCode = HttpStatusCode.OK,
    headers: Headers = headersOf(HttpHeaders.ContentType, "application/json")
) = HttpClient(
    MockEngine { _ ->
        respond(
            content = ByteReadChannel(jsonResponse),
            status = statusCode,
            headers = headers
        )
    }
) {

    install(ContentNegotiation) {
        json(jsonSerializer)
    }

    install(Logging) {
        LogLevel.ALL
    }

    install(DefaultRequest) {
        url(BuildConfig.BACKEND_URL)
    }
}