package com.example.cathaybankairport.main.airportinfo

import BaseFlightPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cathaybankairport.main.airportinfo.api.AirportInfoViewModel

@Composable
fun ArrivalFlightPage() {
    val arrivalFlightPage = remember {
        AirportInfoViewModel("A")
    }
    BaseFlightPage(viewModel = arrivalFlightPage)
}

