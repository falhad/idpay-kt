package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wage(
    @SerialName("amount") val amount: String,
    @SerialName("by") val byWho: String,
    @SerialName("type") val type: String
)