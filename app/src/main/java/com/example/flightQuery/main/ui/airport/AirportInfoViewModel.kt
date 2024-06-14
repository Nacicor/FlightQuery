package com.example.flightQuery.main.ui.airport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import com.example.flightQuery.main.domain.airport.usecase.GetFlightInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AirportInfoViewModel(private val getFlightInfoUseCase: GetFlightInfoUseCase) : ViewModel() {

    private val _flightList = MutableStateFlow(emptyList<AirportInfoItem>())
    val flightList = _flightList.asStateFlow()

    private var currentFlightType: String? = null

    fun setFlightInfo(flightType: String) {
        currentFlightType = flightType
    }

    fun fetchFlightList() {
        val flightType = currentFlightType ?: return
        viewModelScope.launch {
            try {
                val flightInfo = getFlightInfoUseCase.execute(flightType)
                if (flightInfo.isNotEmpty()) {
                    _flightList.value = flightInfo
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}