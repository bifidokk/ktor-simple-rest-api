package com.bifidokk.di

import com.bifidokk.database.DatabaseConnectionFactory
import com.bifidokk.repository.NoteRepository
import com.bifidokk.repository.UserRepository
import com.bifidokk.service.auth.AuthTokenService
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.koin.dsl.module

val appModule = module {
    single<ApplicationConfig> { HoconApplicationConfig(ConfigFactory.load()) }
    single { DatabaseConnectionFactory(get()).apply { init() }.database }
    single { NoteRepository(get()) }
    single { UserRepository(get()) }
    single { AuthTokenService(get(), get()) }
}