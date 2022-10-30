package com.bifidokk.service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.bifidokk.service.User
import io.ktor.server.config.*
import java.util.*

class AuthTokenService(config: ApplicationConfig) {
    private val secret = config.propertyOrNull("jwt.secret")?.getString().orEmpty()

    fun generateJwtToken(user: User): String {
        return JWT.create()
            .withClaim("email", user.email)
            .withClaim("userId", user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + 600))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJwtToken(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(secret))
            .build()
    }
}