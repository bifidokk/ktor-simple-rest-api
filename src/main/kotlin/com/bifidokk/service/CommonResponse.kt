package com.bifidokk.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    @SerialName("data") val data: T? = null,
    @SerialName("message") val message: String? = null
)