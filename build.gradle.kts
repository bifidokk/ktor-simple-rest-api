val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktorm_version: String by project
val postgres_version: String by project
val koin_ktor: String by project
val flyway_version: String by project
val mockk_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    id("io.ktor.plugin") version "2.1.2"
    kotlin("plugin.serialization") version "1.7.20"
    id("org.flywaydb.flyway") version "9.5.1"
}

group = "com.bifidokk"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.ktorm:ktorm-core:$ktorm_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("org.ktorm:ktorm-support-postgresql:$ktorm_version")

    implementation("io.insert-koin:koin-ktor:$koin_ktor")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_ktor")

    implementation("org.flywaydb:flyway-core:$flyway_version")

    implementation ("io.ktor:ktor-server-auth:$ktor_version")
    implementation ("io.ktor:ktor-server-auth-jwt:$ktor_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation ("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.4.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.4.2")

    testImplementation("io.mockk:mockk:$mockk_version")
}

flyway {
    driver = "org.postgresql.Driver"
}