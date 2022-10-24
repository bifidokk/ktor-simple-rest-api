package com.bifidokk.database

import io.ktor.server.config.*
import org.ktorm.database.Database

class DatabaseConnectionFactory(private val config: ApplicationConfig) {
    lateinit var database: Database

    fun init() {
        database = Database.connect(
            url = config.property("db.jdbc_db_url").getString(),
            driver = config.property("db.jdbc_db_driver").getString(),
            user = config.property("db.jdbc_db_user").getString(),
            password = config.property("db.jdbc_db_password").getString(),
        )
    }
}