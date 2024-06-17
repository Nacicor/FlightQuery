package com.example.flightQuery.main.ui.airport.screen

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.flightQuery.R
import com.example.flightQuery.main.ui.airport.AirportInfoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AirportInfoPage() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()

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
            when (page) {
                0 -> {
                    val model: AirportInfoViewModel =
                        koinViewModel()
                    model.setFlightType("D")
                    DepartingFlightPage(model)
                }

                1 -> {
                    val model: AirportInfoViewModel =
                        koinViewModel()
                    model.setFlightType("A")
                    ArrivalFlightPage(model)
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

