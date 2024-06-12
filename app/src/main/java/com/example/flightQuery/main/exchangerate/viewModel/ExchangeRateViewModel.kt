package com.example.flightQuery.main.exchangerate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.exchangerate.api.RateClient
import com.example.flightQuery.main.exchangerate.api.RateDataConvertor
import com.example.flightQuery.main.exchangerate.api.RateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeRateViewModel(
    private val repository: RateRepository,
    private val convertor: RateDataConvertor
) : ViewModel(

) {
    private val _rateList = MutableStateFlow(emptyMap<String, Double>())
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
        val newCurrencyMap = NewCurrencyMap()
        _displayList.value =
            newCurrencyMap.newCurrencyMap(
                _displayList.value,
                _rateList.value,
                selectedCurrency,
                amount
            )
    }


}
