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
            val userLogin = call.receive<UserLogin>()
            call.respondText("Logged in")
        }
    }
}
@Serializable
data class UserLogin(val email: String, val password: String)