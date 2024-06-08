package com.example.cathaybankairport.main.airportinfo

import BaseFlightPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.cathaybankairport.main.airportinfo.api.AirPortInfoViewModel

@Composable
fun DepartingFlightPage() {
    val departingFlightModel = remember {
        AirPortInfoViewModel("D")
    }
    BaseFlightPage(viewModel = departingFlightModel)
}