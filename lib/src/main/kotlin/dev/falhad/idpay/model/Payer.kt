package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Payer(
    @SerialName("desc")   val desc: String,
    @SerialName("mail")   val mail: String,
    @SerialName("name")   val name: String,
    @SerialName("phone")   val phone: String
)