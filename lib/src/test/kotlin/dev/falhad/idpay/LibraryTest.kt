/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.falhad.idpay

import kotlin.test.Test

class LibraryTest {
    @Test
    fun testRequestToken() {
        val idPay = IDPay(apiKey = "", sandbox = true, logging = true)
        val tokenResponse = idPay.requestToken("testOrderId", 1000, "http://localhost:8080/payment")
        tokenResponse.fold({
            println("success")
            println(it)
        },{
            when(it){
                is IDPayException -> println(it)
                else -> println(it.message)
            }
        })
    }
}
