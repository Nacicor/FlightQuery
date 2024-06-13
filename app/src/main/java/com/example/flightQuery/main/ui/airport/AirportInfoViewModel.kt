package com.example.flightQuery.main.ui.airport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.main.data.airport.AirportRepository
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
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
                val flightInfo = AirportRepository().flightInfo(currentFlightType)
                if (flightInfo.isNotEmpty()) {
                    _flightList.value = flightInfo
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}