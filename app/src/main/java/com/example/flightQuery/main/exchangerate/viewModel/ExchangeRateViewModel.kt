package com.example.flightQuery.main.exchangerate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.exchangerate.api.RateClient
import com.example.flightQuery.main.exchangerate.api.RateDataConvertor
import com.example.flightQuery.main.exchangerate.api.RateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeRateViewModel : ViewModel() {

    private val _rateList = MutableStateFlow(emptyMap<String, Double>())
    private val repository = RateRepository()
    private val convertor = RateDataConvertor()
    private val _displayList = MutableStateFlow(emptyMap<String, Double>())
    val displayList = _displayList.asStateFlow()
    private val _isRateCardEnabled = MutableStateFlow(false)
    val isRateCardEnabled = _isRateCardEnabled.asStateFlow()

    init {
        fetchCurrencyRate()
    }

    private fun fetchCurrencyRate() {
        viewModelScope.launch {
            val allRate: Map<String, Double> =
                convertor.convertResponseToMap(repository.getFromApiResult(RateClient()))
                    .mapValues { (_, value) -> value as? Double ?: 1.0 }
            _rateList.value = allRate
            _displayList.value = allRate
            _isRateCardEnabled.value = true
        }
    }

    fun updateCurrencyAmounts(
        selectedCurrency: String,
        amount: Double = 0.0,
    ) {
        _displayList.value =
            newCurrencyMap(_displayList.value, _rateList.value, selectedCurrency, amount)
    }

    private fun newCurrencyMap(
        displayMap: Map<String, Double>,
        rateMap: Map<String, Double>,
        selectedCurrency: String,
        chooseAmount: Double
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
                    BigDecimal(chooseCurrency * targetCurrency / chooseCurrency).setScale(
                        2, RoundingMode.HALF_UP
                    ).toDouble()
            }
        }
        return currentMap
    }
}
