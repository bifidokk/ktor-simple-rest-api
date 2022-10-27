package com.bifidokk.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bifidokk.repository.NoteRepository
import com.bifidokk.service.CommonResponse
import com.bifidokk.service.auth.AuthTokenResponse
import com.bifidokk.service.auth.UserCredentialsRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRouting(
    noteRepository: NoteRepository,
    config: ApplicationConfig
) {
    routing {
        get("/") {
            val notes = noteRepository.getAllNotes()
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = notes
                )
            )
        }

        post("/auth") {
            val userCredentials = call.receive<UserCredentialsRequest>()


            val token = JWT.create()
                .withClaim("email", userCredentials.email)
                .withExpiresAt(Date(System.currentTimeMillis() + 600))
                .sign(Algorithm.HMAC256(config.property("jwt.secret").getString()))

            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = AuthTokenResponse(token)
                )
            )
        }
    }
}