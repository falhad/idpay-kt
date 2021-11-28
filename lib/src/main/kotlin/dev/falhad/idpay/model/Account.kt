package dev.falhad.idpay.model

data class Account(
    val bank: Bank,
    val iban: String,
    val id: String,
    val number: String
)