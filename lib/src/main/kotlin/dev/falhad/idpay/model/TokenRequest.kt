package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    @SerialName("order_id") val orderId: String,
    @SerialName("amount") val amount: Int,
    @SerialName("callback") val callback: String,
    @SerialName("desc") val desc: String? = null,
    @SerialName("mail") val mail: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("phone") val phone: String? = null,
)