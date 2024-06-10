package com.example.cathaybankairport.main.exchangerate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cathaybankairport.main.exchangerate.api.RateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeRateViewModel : ViewModel() {

    private val _rateList = MutableStateFlow(emptyMap<String, Double>())
    
    private val repository = RateRepository()

    private val _displayList = MutableStateFlow(emptyMap<String, Double>())
    val displayList = _displayList.asStateFlow()

    init {
        fetchCurrencyRate()
    }

    private fun fetchCurrencyRate() {
        viewModelScope.launch {
            val allRate: Map<String, Double> =
                repository.getCurrencyRate().mapValues { (_, value) -> value as? Double ?: 1.0 }
            _rateList.value = allRate
            _displayList.value = allRate
        }
    }

    fun updateCurrencyAmounts(
        selectedCurrency: String,
        amount: Double = 0.0,
    ) {
        for (item in _displayList.value) {
            val currentMap = _displayList.value.toMutableMap()
            if (item.key == selectedCurrency) {
                currentMap[selectedCurrency] = amount
            } else {
                //AUD to USD = amount * rateList[USD]/rateList[AUD]
                val targetCurrency: Double = _rateList.value[item.key] ?: 1.0
                val chooseCurrency: Double = _rateList.value[selectedCurrency] ?: 1.0

                currentMap[item.key] =
                    BigDecimal(amount * targetCurrency / chooseCurrency).setScale(
                        2, RoundingMode.HALF_UP
                    ).toDouble()
            }

            _displayList.value = currentMap.toMap()
        }
    }

}