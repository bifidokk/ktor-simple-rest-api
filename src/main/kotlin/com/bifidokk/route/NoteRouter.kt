package com.bifidokk.route

import com.bifidokk.repository.NoteRepository
import com.bifidokk.service.CommonResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.noteRouter(noteRepository: NoteRepository) {
    get("/") {
        val notes = noteRepository.getAllNotes()
        call.respond(
            HttpStatusCode.OK, CommonResponse(
                data = notes
            )
        )
    }
}