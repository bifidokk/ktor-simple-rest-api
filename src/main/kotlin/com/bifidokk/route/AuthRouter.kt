package com.bifidokk.route

import com.bifidokk.repository.UserRepository
import com.bifidokk.service.CommonResponse
import com.bifidokk.service.auth.AuthTokenResponse
import com.bifidokk.service.auth.AuthTokenService
import com.bifidokk.service.auth.UserCredentialsRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouter(
    userRepository: UserRepository,
    authTokenService: AuthTokenService
) {
    post("/auth") {
        val userCredentials = call.receive<UserCredentialsRequest>()
        val user = userRepository.findUserByEmail(userCredentials.email)

        if (user == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                CommonResponse(data = "null", message = "Invalid username/password")
            )

            return@post
        }

        val token = authTokenService.generateJwtToken(user)

        call.respond(
            HttpStatusCode.OK, CommonResponse(
                data = AuthTokenResponse(token)
            )
        )
    }
}