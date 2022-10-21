package com.bifidokk.di

import com.bifidokk.database.DatabaseConnectionFactory
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.koin.dsl.module

val appModule = module {
    single<ApplicationConfig> { HoconApplicationConfig(ConfigFactory.load()) }
    single { DatabaseConnectionFactory(get()).apply { init() }.database }
}