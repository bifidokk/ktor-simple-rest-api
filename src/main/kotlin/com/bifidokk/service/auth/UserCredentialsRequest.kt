package com.bifidokk.service.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialsRequest(
    val email: String
)