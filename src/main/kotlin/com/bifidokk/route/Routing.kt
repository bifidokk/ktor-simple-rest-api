package com.bifidokk.route

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting() {
    routing {
        noteRouter(get())
        authRouter(get(), get())
    }
}