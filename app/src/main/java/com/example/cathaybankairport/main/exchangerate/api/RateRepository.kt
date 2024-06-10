package com.example.cathaybankairport.main.exchangerate.api

import android.util.Log
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class RateRepository {
    suspend fun getCurrencyRate(): Map<String, Any?> {
        RateClient.api.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX")
            .let { response ->
                if (response.isSuccessful) {
                    return toMap(response.body()?.data ?: emptyMap<String, Double>())
                } else {
                    Log.d("RateRepository", "Failure : ${response.code()}")
                }
            }
        return emptyMap<String, Double>()
    }

    private fun <T : Any> toMap(obj: T): Map<String, Any?> {
        return (obj::class as KClass<T>).memberProperties.associate { prop ->
            prop.name to prop.get(obj)?.let { value ->
                if (value::class.isData) {
                    toMap(value)
                } else {
                    value
                }
            }
        }
    }
}