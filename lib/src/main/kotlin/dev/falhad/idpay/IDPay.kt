/*
 * Copyright (c) 2021.
 * Farhad Navayazdan
 * cs.arcxx@gmail.com
 */
package dev.falhad.idpay

import dev.falhad.idpay.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking


const val URL_GENERATE_TOKEN = "https://api.idpay.ir/v1.1/payment"
const val URL_VERIFY_PAYMENT = "https://api.idpay.ir/v1.1/payment/verify"
const val URL_INQUIRY_PAYMENT = "https://api.idpay.ir/v1.1/payment/inquiry"
const val URL_PAYMENT_LIST = "https://api.idpay.ir/v1.1/payment/transactions?page={page}&page_size={size}"


class IDPay(private val apiKey: String, private val sandbox: Boolean = false, logging: Boolean = false) {

    private val client = HttpClient(CIO) {
        expectSuccess = false
        engine {}

        defaultRequest {
            if (!sandbox) {
                header("X-API-KEY", apiKey)
            } else {
                header("X-SANDBOX", sandbox)
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        if (logging) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }

    }


    /**
     * @param   orderId    شماره سفارش پذیرنده - max 50 chars
     * @param   amount            مبلغ مورد نظر به ریال
     * @param   name            نام پرداخت کننده
     * @param   phone            تلفن همراه پرداخت کننده / 11 char
     *   مثل 9382198592 یا 09382198592 یا 989382198592
     * @param   mail            پست الکترونیک پرداخت کننده
     * @param   desc            توضیح تراکنش
     * @param   callback  آدرس بازگشت به سایت پذیرنده
     */
    fun requestToken(
        orderId: String,
        amount: Int,
        callback: String,
        name: String? = null,
        phone: String? = null,
        mail: String? = null,
        desc: String? = null,
    ): Result<TokenResponse> = runBlocking {
        try {
            val response = client.post<HttpResponse>(URL_GENERATE_TOKEN) {
                contentType(ContentType.Application.Json)
                body = TokenRequest(
                    orderId = orderId,
                    amount = amount,
                    callback = callback,
                    desc = desc,
                    mail = mail,
                    name = name,
                    phone = phone
                )
            }
            if (response.status.isSuccess()) {
                val tokenResponse = response.receive<TokenResponse>()
                Result.success(tokenResponse)
            } else {
                val idPayException = response.receive<IDPayException>()
                Result.failure(idPayException)
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


    fun verify(orderId: String, id: String): Result<VerifyResponse> = runBlocking {
        try {
            val response = client.post<HttpResponse>(URL_VERIFY_PAYMENT) {
                contentType(ContentType.Application.Json)
                body = VerifyRequest(
                    orderId = orderId, id = id
                )
            }
            if (response.status.isSuccess()) {
                val tokenResponse = response.receive<VerifyResponse>()
                Result.success(tokenResponse)
            } else {
                val idPayException = response.receive<IDPayException>()
                Result.failure(idPayException)
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


    fun inquiry(orderId: String, id: String): Result<InquiryResponse> = runBlocking {
        try {
            val response = client.post<HttpResponse>(URL_INQUIRY_PAYMENT) {
                contentType(ContentType.Application.Json)
                body = InquiryRequest(
                    orderId = orderId, id = id
                )
            }
            if (response.status.isSuccess()) {
                val tokenResponse = response.receive<InquiryResponse>()
                Result.success(tokenResponse)
            } else {
                val idPayException = response.receive<IDPayException>()
                Result.failure(idPayException)
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


    fun transactions(
        page: Int?, pageSize: Int?,
        orderId: String? = null,
        id: String? = null,
        amount: Int? = null,
        status: List<TransactionStatus>? = null,
        trackId: String? = null,
        paymentCardNo: String? = null,
        paymentHashedCardNo: String? = null,
        paymentDate: PaymentDate? = null,
        settlementDate: PaymentDate? = null,
    ): Result<TransactionResponse> = runBlocking {
        try {
            val response =
                client.post<HttpResponse>(
                    URL_PAYMENT_LIST.replace("{page}", "${page ?: 0}").replace("{size}", "${pageSize ?: 25}")
                ) {
                    contentType(ContentType.Application.Json)
                    body = TransactionsRequest(
                        orderId = orderId,
                        id = id,
                        amount = amount,
                        status = status,
                        trackId = trackId,
                        paymentCardNo = paymentCardNo,
                        paymentHashedCardNo = paymentHashedCardNo,
                        paymentDate = paymentDate,
                        settlementDate = settlementDate
                    )
                }
            if (response.status.isSuccess()) {
                val tokenResponse = response.receive<TransactionResponse>()
                Result.success(tokenResponse)
            } else {
                val idPayException = response.receive<IDPayException>()
                Result.failure(idPayException)
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


}

