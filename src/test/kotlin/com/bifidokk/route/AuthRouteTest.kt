package com.bifidokk.route

import com.bifidokk.repository.UserRepository
import com.bifidokk.service.auth.AuthTokenService
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Routing
import io.ktor.server.testing.ApplicationTestBuilder
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.module.Module
import org.koin.dsl.module

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthRouteTest: BaseRoutingTest() {

    private val userRepository: UserRepository = mockk()
    private val authTokenService: AuthTokenService = mockk()

    override val koinModules: Module = module {
        single { userRepository }
        single { authTokenService }
    }

    override val moduleList: ApplicationTestBuilder.() -> Unit = {
        install(Routing) {
            authRouter(userRepository, authTokenService)
        }
    }

    @BeforeEach
    fun clearMocks() {
        io.mockk.clearMocks(userRepository, authTokenService)
    }

    @Test
    fun `it returns 400 error if a user does not exist`() = withBaseTestApplication {
        coEvery { userRepository.findUserByEmail(any()) } returns null

        val response = customClient.post("/auth") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody("{\"email\": \"user@user.com\"}")
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}