package com.example.cathaybankairport.main.airportinfo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cathaybankairport.main.airportinfo.AirportInfoViewModel

@Composable
fun DepartingFlightPage() {
    val departingFlightModel = remember {
        AirportInfoViewModel("D")
    }
    BaseFlightPage(viewModel = departingFlightModel)
}