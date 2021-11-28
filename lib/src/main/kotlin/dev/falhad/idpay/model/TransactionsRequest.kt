package dev.falhad.idpay.model

import dev.falhad.idpay.TransactionStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsRequest(
    @SerialName("order_id") val orderId: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("amount") val amount: Int? = null,
    @SerialName("status") val status: List<TransactionStatus>? = null,
    @SerialName("track_id") val trackId: String? = null,
    @SerialName("payment_card_no") val paymentCardNo: String? = null,
    @SerialName("payment_hashed_card_no") val paymentHashedCardNo: String? = null,
    @SerialName("payment_date") val paymentDate: PaymentDate? = null,
    @SerialName("settlement_date") val settlementDate: PaymentDate? = null,

    )

@Serializable
data class PaymentDate(
    @SerialName("min") val min: Int,
    @SerialName("max") val max: Int,
)

