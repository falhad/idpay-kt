package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Verify(
    @SerialName("date") val date: String
)