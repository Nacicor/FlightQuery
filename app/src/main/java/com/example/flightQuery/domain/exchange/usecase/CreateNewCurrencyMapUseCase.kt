package com.example.flightQuery.domain.exchange.usecase

import java.math.BigDecimal
import java.math.RoundingMode

class CreateNewCurrencyMapUseCase {
    fun create(
        displayMap: Map<String, Double>,
        rateMap: Map<String, Double>,
        selectedCurrency: String,
        chooseAmount: Double,
    ): Map<String, Double> {
        val currentMap = displayMap.toMutableMap()
        for (item in displayMap) {
            if (item.key == selectedCurrency) {
                currentMap[selectedCurrency] = chooseAmount
            } else {
                //AUD to USD = amount * rateList[USD]/rateList[AUD]
                val targetCurrency: Double = rateMap[item.key] ?: 1.0
                val chooseCurrency: Double = rateMap[selectedCurrency] ?: 1.0

                currentMap[item.key] =
                    BigDecimal(chooseAmount * targetCurrency / chooseCurrency).setScale(
                        2, RoundingMode.HALF_UP
                    ).toDouble()
            }
        }
        return currentMap
    }
}