package com.example.cathaybankairport.main.airportinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cathaybankairport.main.airportinfo.api.AirportInfoItem
import com.example.cathaybankairport.main.airportinfo.api.AirportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AirportInfoViewModel(flightType: String) : ViewModel() {

    private val _flightList = MutableStateFlow(emptyList<AirportInfoItem>())
    val flightList = _flightList.asStateFlow()
    private val currentFlightType = flightType

    fun fetchFlightList() {
        viewModelScope.launch {
            try {
                val flightInfo = AirportRepository().FlightInfo(currentFlightType)
                if (flightInfo.isNotEmpty()) {
                    _flightList.value = flightInfo
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}