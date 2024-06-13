package com.example.flightQuery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.flightQuery.main.airportinfo.view.AirportInfoPage
import com.example.flightQuery.main.exchangerate.view.ExchangeRatePage
import com.example.flightQuery.main.exchangerate.viewModel.ExchangeRateViewModel
import com.example.flightQuery.main.navigationbar.MyNavigationBar
import com.example.flightQuery.ui.theme.FlightQueryTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exchangeRateViewModel: ExchangeRateViewModel by viewModel()
        setContent {
            FlightQueryTheme {
                var selectItemIndex by remember {
                    mutableIntStateOf(0)
                }
                val pagerState = rememberPagerState(pageCount = { 2 })
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            MyNavigationBar(selectedItemIndex = selectItemIndex) {
                                selectItemIndex = it
                            }
                        }
                    ) {
                        PaddedBox(innerPadding = it) {
                            when (selectItemIndex) {
                                0 -> {
                                    AirportInfoPage()
                                }

                                1 -> {
                                    ExchangeRatePage(exchangeRateViewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PaddedBox(innerPadding: PaddingValues, content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(paddingValues = innerPadding)) {
        content()
    }
}
