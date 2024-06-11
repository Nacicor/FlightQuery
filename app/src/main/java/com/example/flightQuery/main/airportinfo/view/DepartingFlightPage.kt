package com.example.flightQuery.main.airportinfo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.flightQuery.main.airportinfo.AirportInfoViewModel

@Composable
fun DepartingFlightPage() {
    val departingFlightModel = remember {
        AirportInfoViewModel("D")
    }
    BaseFlightPage(viewModel = departingFlightModel)
}