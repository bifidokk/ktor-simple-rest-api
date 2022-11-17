package com.bifidokk

import io.ktor.http.*
import io.ktor.client.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.junit.After
import org.koin.core.context.stopKoin

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}