package com.bifidokk.service.note

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("id") val id: Int? = null,
    @SerialName("note") val note: String
)