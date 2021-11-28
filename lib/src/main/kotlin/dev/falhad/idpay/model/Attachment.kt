package dev.falhad.idpay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("current_page") val currentPage: Int,
    @SerialName("page_amount") val pageAmount: Int,
    @SerialName("page_count") val pageCount: Int,
    @SerialName("timestamp") val timestamp: Int,
    @SerialName("total_amount") val totalAmount: String,
    @SerialName("total_count") val totalCount: Int
)