package com.example.flightQuery.main.exchangerate.api

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

data class RateInfoItem(
    val data: Data
) {
    data class Data(
        @SerializedName("AUD")
        val aud: Double = 1.0,
        @SerializedName("BGN")
        val bgn: Double = 1.0,
        @SerializedName("BRL")
        val brl: Double = 1.0,
        @SerializedName("CAD")
        val cad: Double = 1.0,
        @SerializedName("CHF")
        val chf: Double = 1.0,
        @SerializedName("CNY")
        val cny: Double = 1.0,
        @SerializedName("CZK")
        val czk: Double = 1.0,
        @SerializedName("DKK")
        val dkk: Double = 1.0,
        @SerializedName("EUR")
        val eur: Double = 1.0,
        @SerializedName("GBP")
        val gbp: Double = 1.0,
        @SerializedName("HKD")
        val hkd: Double = 1.0,
        @SerializedName("HRK")
        val hrk: Double = 1.0,
        @SerializedName("HUF")
        val huf: Double = 1.0,
        @SerializedName("IDR")
        val idr: Double = 1.0,
        @SerializedName("ILS")
        val ils: Double = 1.0,
        @SerializedName("INR")
        val inr: Double = 1.0,
        @SerializedName("ISK")
        val isk: Double = 1.0,
        @SerializedName("JPY")
        val jpy: Double = 1.0,
        @SerializedName("KRW")
        val krw: Double = 1.0,
        @SerializedName("MXN")
        val mxn: Double = 1.0,
        @SerializedName("MYR")
        val myr: Double = 1.0,
        @SerializedName("NOK")
        val nok: Double = 1.0,
        @SerializedName("NZD")
        val nzd: Double = 1.0,
        @SerializedName("PHP")
        val php: Double = 1.0,
        @SerializedName("PLN")
        val pln: Double = 1.0,
        @SerializedName("RON")
        val ron: Double = 1.0,
        @SerializedName("RUB")
        val rub: Double = 1.0,
        @SerializedName("SEK")
        val sek: Double = 1.0,
        @SerializedName("SGD")
        val sgd: Double = 1.0,
        @SerializedName("THB")
        val thb: Double = 1.0,
        @SerializedName("TRY")
        val `try`: Double = 1.0,
        @SerializedName("USD")
        val usd: Double = 1.0,
        @SerializedName("ZAR")
        val zar: Double = 1.0
    ) {
        companion object {
            fun getCurrencies(): List<String> {
                return Data::class.memberProperties.map(KProperty1<Data, *>::name)
            }
        }
    }
}

