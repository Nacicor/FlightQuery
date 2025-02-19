package com.example.flightQuery.ui.airport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.domain.airport.model.AirportInfoItem
import com.example.flightQuery.domain.airport.usecase.FetchAirportInfoUseCase
import com.example.flightQuery.domain.airport.usecase.GetAirportInfoResponseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirportInfoViewModel @Inject constructor(
    private val fetchAirportInfoUseCase: FetchAirportInfoUseCase,
    private val getAirportResponseUseCase: GetAirportInfoResponseUseCase
) : ViewModel() {

    private val _flightList = MutableStateFlow(emptyList<AirportInfoItem>())
    val flightList = _flightList.asStateFlow()

    private var flightType: String = ""
    fun setFlightType(type: String) {
        flightType = type
    }

    fun fetchFlightList() {
        println("============fetchFlightList = $flightType")
        viewModelScope.launch {
            try {
                val flightInfoResponse = fetchAirportInfoUseCase.execute(flightType)
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