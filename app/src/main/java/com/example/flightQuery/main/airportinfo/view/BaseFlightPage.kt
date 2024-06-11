package com.example.flightQuery.main.airportinfo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightQuery.main.airportinfo.AirportInfoItem
import com.example.flightQuery.main.airportinfo.AirportInfoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseFlightPage(viewModel: AirportInfoViewModel) {

    val data = viewModel.flightList.collectAsState()

    val refreshState = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchFlightList()
    }
    PullToRefreshBox(
        isRefreshing = refreshState.value,
        onRefresh = {
            scope.launch {
                refreshState.value = true
                viewModel.fetchFlightList()
                delay(1500)
                refreshState.value = false
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!refreshState.value) {
                if (data.value.isNotEmpty()) {
                    items(data.value) {
                        AirportInfoItem(it)
                    }
                }
            }
        }
    }
}