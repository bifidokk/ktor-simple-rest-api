package com.bifidokk.service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.bifidokk.repository.UserRepository
import com.bifidokk.service.user.User
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import java.util.*

class AuthTokenService(
    config: ApplicationConfig,
    private val userRepository: UserRepository
) {
    private val secret = config.propertyOrNull("jwt.secret")?.getString().orEmpty()

    fun generateJwtToken(user: User): String {
        return JWT.create()
            .withClaim("email", user.email)
            .withClaim("userId", user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + 600_000))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJwtToken(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(secret))
            .build()
    }

    fun getAuthenticatedUser(jwtPrincipal: JWTPrincipal?): User? {
        if (jwtPrincipal == null) {
            return null
        }

        val userId = jwtPrincipal.payload.getClaim("userId").asInt()

        return userRepository.findUserById(userId)
    }

    fun getJwtPrincipal(jwtCredential: JWTCredential): JWTPrincipal? {
        val userId = jwtCredential.payload.getClaim("userId").asInt()

        userRepository.findUserById(userId) ?: return null

        return JWTPrincipal(jwtCredential.payload)
    }
}