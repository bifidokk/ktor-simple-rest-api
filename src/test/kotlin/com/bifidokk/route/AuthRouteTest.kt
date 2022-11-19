package com.bifidokk.route

import com.bifidokk.repository.UserRepository
import com.bifidokk.service.auth.AuthTokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.dsl.module

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthRouteTest: BaseRoutingTest() {

    private val userRepository: UserRepository = mockk()
    private val authTokenService: AuthTokenService = mockk()

    @BeforeAll
    fun setup() {
        koinModules = module {
            single { userRepository }
        }

        moduleList = {
            install(Routing) {
                authRouter(userRepository, authTokenService)
            }
        }
    }

    @BeforeEach
    fun clearMocks() {
        io.mockk.clearMocks(userRepository, authTokenService)
    }

    @Test
    fun `it returns 400 error if a user does not exist`() = withBaseTestApplication {
        coEvery { userRepository.findUserByEmail(any()) } returns null

        val call = handleRequest(HttpMethod.Post, "/auth") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody("{\"email\": \"user@user.com\"}")
        }
        assertEquals(HttpStatusCode.BadRequest, call.response.status())
    }
}