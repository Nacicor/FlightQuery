package com.example.cathaybankairport.main.airportinfo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AirportInfoPage(){
    val pagerState = rememberPagerState(pageCount = { 2 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ) {page->
        when(page){
            0 -> DepartingFlightPage()
            1 -> ArrivalFlightPage()
        }
    }
}

