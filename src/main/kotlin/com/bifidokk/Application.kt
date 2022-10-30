package com.bifidokk

import com.auth0.jwt.algorithms.*
import com.bifidokk.di.appModule
import com.bifidokk.route.configureRouting
import com.bifidokk.service.auth.AuthTokenService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import org.koin.logger.slf4jLogger
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(appModule))
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    val authTokenService by inject<AuthTokenService>()
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(
                authTokenService.verifyJwtToken()
            )
        }
    }
    configureRouting()
}

