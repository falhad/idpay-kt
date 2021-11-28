package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("link") val link: String? = null,
)