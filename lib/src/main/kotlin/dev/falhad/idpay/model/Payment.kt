package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    @SerialName("amount")  val amount: String,
    @SerialName("card_no")  val cardNo: String,
    @SerialName("date")  val date: String,
    @SerialName("hashed_card_no")  val hashedCardNo: String,
    @SerialName("track_id")  val trackId: String
)