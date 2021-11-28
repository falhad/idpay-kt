package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Settlement(
    @SerialName("amount")  val amount: String,
    @SerialName("date")  val date: String,
    @SerialName("track_id")  val trackId: String
)