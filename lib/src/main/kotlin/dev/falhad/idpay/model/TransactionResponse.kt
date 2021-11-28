package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    @SerialName("attachment")  val attachment: Attachment,
    @SerialName("records")  val records: List<Record>
)