package com.example.cathaybankairport.main.airportinfo.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AirPortInfoViewModel(flightType:String) : ViewModel() {

    private val _flightList = MutableStateFlow(emptyList<AirPortInfoItem>())
    val flightList = _flightList.asStateFlow()
    private val currentFlightType = flightType

    fun fetchFlightList() {
        viewModelScope.launch {
            try {
                ApiClient.api.getFlightAllInfo(currentFlightType, "TPE").let { response ->
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            _flightList.value = response.body()!!
                            println(response.body())
                        } else {
                        }

                    } else {
                        Log.d("DataViewModel", "onFailure: ${response.message()}")
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}