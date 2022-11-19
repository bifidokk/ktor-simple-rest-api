package com.bifidokk.route

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin

abstract class BaseRoutingTest {
    protected var koinModules: Module? = null
    protected var moduleList: Application.() -> Unit = { }

    init {
        stopKoin()
    }

    fun <R> withBaseTestApplication(test: TestApplicationEngine.() -> R) {
        withTestApplication({
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            koinModules?.let {
                install(Koin) {
                    modules(it)
                }
            }
            moduleList()
        }) {
            test()
        }
    }
}