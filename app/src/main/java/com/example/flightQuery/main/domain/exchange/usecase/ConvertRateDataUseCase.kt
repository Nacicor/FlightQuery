package com.example.flightQuery.main.domain.exchange.usecase

import android.util.Log
import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class ConvertRateDataUseCase {

    fun convertResponseToMap(response: Response<RateInfoItem>): Map<String, Any?> {
        response.let {
            if (it.isSuccessful) {
                return toMap(it.body()?.data ?: emptyMap<String, Double>())
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