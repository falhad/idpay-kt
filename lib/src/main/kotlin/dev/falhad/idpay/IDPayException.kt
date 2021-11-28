package dev.falhad.idpay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IDPayException(
    @SerialName("error_code") val code: Int,
    @SerialName("error_message") val msg: String
) : Throwable(message = msg)