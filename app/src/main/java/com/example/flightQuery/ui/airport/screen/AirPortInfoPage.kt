package com.example.flightQuery.ui.airport.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.flightQuery.R
import com.example.flightQuery.ui.airport.AirportInfoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AirportInfoPage() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()
    var flightType by remember { mutableStateOf("D") }

    Column {
        Box(modifier = Modifier.padding(8.dp)) {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                val tabs = listOf("起飛班機", "抵達班機")
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = pagerState.currentPage == index,
                        icon = {
                            Icon(
                                imageVector = allIcon(page = index),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) { page ->
            val viewModel: AirportInfoViewModel = koinViewModel()

            LaunchedEffect(page) {
                flightType = if (page == 0) "D" else "A"
                viewModel.setFlightType(flightType)
            }
            when (page) {
                0 -> {
                    DepartingFlightPage(viewModel)
                }

                1 -> {
                    ArrivalFlightPage(viewModel)
                }
            }
        }
    }
}

@Composable
fun allIcon(page: Int): ImageVector {
    return when (page) {
        0 -> ImageVector.vectorResource(id = R.drawable.flight_takeoff_24dp_fill0_wght400_grad0_opsz24__1_)
        1 -> ImageVector.vectorResource(id = R.drawable.flight_land_24dp_fill0_wght400_grad0_opsz24__1_)
        else -> {
            Icons.Default.Build
        }
    }
}

@Composable
fun ArrivalFlightPage(model: AirportInfoViewModel) {
    BaseFlightPage(viewModel = model)
}

@Composable
fun DepartingFlightPage(model: AirportInfoViewModel) {
    BaseFlightPage(viewModel = model)
}

