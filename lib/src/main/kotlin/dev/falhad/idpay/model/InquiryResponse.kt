package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InquiryResponse(
    @SerialName("amount") val amount: String,
    @SerialName("date") val date: String,
    @SerialName("id") val id: String,
    @SerialName("order_id") val orderId: String,
    @SerialName("payer") val payer: Payer,
    @SerialName("payment") val payment: Payment,
    @SerialName("settlement") val settlement: Settlement,
    @SerialName("status") val status: String,
    @SerialName("track_id") val trackId: String,
    @SerialName("verify") val verify: Verify,
    @SerialName("wage") val wage: Wage
)