package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InquiryRequest(
    @SerialName("order_id") val orderId: String,
    @SerialName("id") val id: String,
)