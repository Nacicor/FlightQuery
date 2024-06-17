package com.example.flightQuery.main.ui.airport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import com.example.flightQuery.main.domain.airport.usecase.FetchAirportInfoUseCase
import com.example.flightQuery.main.domain.airport.usecase.GetAirportInfoResponseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AirportInfoViewModel(
    private val fetchAirportInfoUseCase: FetchAirportInfoUseCase,
    private val getAirportResponseUseCase: GetAirportInfoResponseUseCase
) : ViewModel() {

    private val _flightList = MutableStateFlow(emptyList<AirportInfoItem>())
    val flightList = _flightList.asStateFlow()

    private var currentFlightType: String? = null

    fun setFlightType(flightType: String) {
        currentFlightType = flightType
    }

    fun fetchFlightList() {
        viewModelScope.launch {
            try {
                val flightInfoResponse = fetchAirportInfoUseCase.execute(currentFlightType ?: "D")
                val flightInfo = getAirportResponseUseCase.processResponse(flightInfoResponse)
                if (flightInfo.isNotEmpty()) {
                    _flightList.value = flightInfo
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}