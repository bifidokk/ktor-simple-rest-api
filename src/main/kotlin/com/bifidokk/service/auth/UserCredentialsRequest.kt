package com.bifidokk.service.auth

@kotlinx.serialization.Serializable
data class UserCredentialsRequest(
    val email: String
)