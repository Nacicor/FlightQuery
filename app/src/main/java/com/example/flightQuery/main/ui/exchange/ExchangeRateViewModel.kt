package com.example.flightQuery.main.ui.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.data.exchange.RateRepository
import com.example.flightQuery.main.data.exchange.RateRetrofitInstance
import com.example.flightQuery.main.domain.exchange.usecase.ConvertRateDataUseCase
import com.example.flightQuery.main.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeRateViewModel(
    private val repository: RateRepository,
    private val convertor: ConvertRateDataUseCase,
    private val createCurrencyMap: CreateNewCurrencyMapUseCase
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
                convertor.convertResponseToMap(repository.getFromApiResult(RateRetrofitInstance))
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
        _displayList.value = createCurrencyMap.create(
            _displayList.value, _rateList.value, selectedCurrency, amount
        )
    }

}
