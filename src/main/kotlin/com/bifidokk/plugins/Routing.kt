package com.bifidokk.plugins

import com.bifidokk.repository.NoteRepository
import com.bifidokk.service.CommonResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.*

fun Application.configureRouting(noteRepository: NoteRepository) {
    routing {
        get("/") {
            val notes = noteRepository.getAllNotes()
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = notes, message = "Success"
                )
            )
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
