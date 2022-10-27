package com.bifidokk.service.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse (
    val token: String
)