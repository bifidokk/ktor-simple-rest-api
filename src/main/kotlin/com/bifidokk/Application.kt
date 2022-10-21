package com.bifidokk

import com.bifidokk.di.appModule
import com.bifidokk.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.logger.slf4jLogger
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused")
fun Application.module(koinModules: List<Module> = listOf(appModule)) {
    install(Koin) {
        slf4jLogger()
        modules(koinModules)
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    configureRouting()
}

