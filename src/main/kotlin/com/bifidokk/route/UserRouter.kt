package com.bifidokk.route

import com.bifidokk.service.CommonResponse
import com.bifidokk.service.auth.AuthTokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouter(
    authTokenService: AuthTokenService
) {
    authenticate("auth-jwt") {
        get("/me") {
            val user = authTokenService.getAuthenticatedUser(call.principal())

            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = user
                )
            )
        }
    }
}