package com.bifidokk.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("Hello")
        }

        post("/login"){
            val userLogin = call.receive<UserLoginRequest>()
            call.respond(UserLoginResponse(userLogin.email))
        }
    }
}

@Serializable
data class UserLoginRequest(val email: String, val password: String)

@Serializable
data class UserLoginResponse(val email: String)
