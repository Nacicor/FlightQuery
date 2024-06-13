package com.example.flightQuery.main.exchangerate.viewModel

import com.example.flightQuery.main.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode


class ExchangeRateViewModelTest {
    @Test
    fun testUpdateCurrencyAmounts() {
        runTest {
            val newCurrencyMap = CreateNewCurrencyMapUseCase()

            val fakeDisplay = mapOf("USD" to 1.0, "AUD" to 1.5)
            val fakeRateList = mapOf("USD" to 1.0, "AUD" to 0.75)

            val selectedCurrency = "USD"
            val amount = 100.0

            val fakeResult = mutableMapOf(
                "USD" to amount,
                "AUD" to BigDecimal(amount * fakeRateList["AUD"]!! / fakeRateList["USD"]!!).setScale(
                    2, RoundingMode.HALF_UP
                ).toDouble()
            )
            val actualResult =
                newCurrencyMap.create(fakeDisplay, fakeRateList, selectedCurrency, amount)

            assertEquals(fakeResult, actualResult)
        }
    }
}