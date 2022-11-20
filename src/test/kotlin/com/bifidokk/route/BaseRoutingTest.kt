package com.bifidokk.route

import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation

abstract class BaseRoutingTest {
    abstract val koinModules: Module
    abstract val moduleList: ApplicationTestBuilder.() -> Unit

    @BeforeEach
    fun shutdownKoin() {
        stopKoin()
    }

    fun <R> withBaseTestApplication(test: suspend ApplicationTestBuilder.() -> R) {
        testApplication {
            environment {
                config = MapApplicationConfig("ktor.environment" to "test")
            }

            koinModules.let {
                install(Koin) {
                    modules(it)
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            moduleList()
            test()
        }
    }
}

val ApplicationTestBuilder.customClient: HttpClient
    get() = createClient {
        install(ClientContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }