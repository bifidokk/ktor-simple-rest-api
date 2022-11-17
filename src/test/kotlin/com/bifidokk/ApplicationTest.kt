package com.bifidokk

import com.bifidokk.service.auth.UserCredentialsRequest
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
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

    @Test
    fun `when log in user, we return response token body`() = testApplication {

        val client = createClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        val response = client.post("/auth") {
            contentType(ContentType.Application.Json)
            setBody(UserCredentialsRequest("user@user.com"))
        }

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}