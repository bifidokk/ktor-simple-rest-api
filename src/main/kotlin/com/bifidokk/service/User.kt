package com.bifidokk.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User (
    @SerialName("id") val id: Int? = null,
    @SerialName("email") val email: String
)