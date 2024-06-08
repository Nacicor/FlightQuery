package com.example.cathaybankairport.main.airportinfo

import BaseFlightPage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cathaybankairport.main.airportinfo.api.AirPortInfoViewModel

@Composable
fun ArrivalFlightPage() {
    val arrivalFlightPage = remember {
        AirPortInfoViewModel("A")
    }
    BaseFlightPage(viewModel = arrivalFlightPage)
}

