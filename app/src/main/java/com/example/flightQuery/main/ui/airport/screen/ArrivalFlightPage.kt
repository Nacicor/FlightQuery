package com.example.flightQuery.main.ui.airport.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.flightQuery.main.ui.airport.AirportInfoViewModel

@Composable
fun ArrivalFlightPage() {
    val arrivalFlightPage = remember {
        AirportInfoViewModel("A")
    }
    BaseFlightPage(viewModel = arrivalFlightPage)
}

