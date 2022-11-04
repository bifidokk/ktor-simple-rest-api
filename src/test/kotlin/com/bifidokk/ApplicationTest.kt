package com.bifidokk

import io.ktor.http.*
import io.ktor.client.request.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
    }
}