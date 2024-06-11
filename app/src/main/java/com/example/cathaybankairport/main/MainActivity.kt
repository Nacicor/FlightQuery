package com.example.cathaybankairport.main

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
import com.example.cathaybankairport.main.airportinfo.view.AirportInfoPage
import com.example.cathaybankairport.main.exchangerate.view.ExchangeRatePage
import com.example.cathaybankairport.main.navigationbar.MyNavigationBar
import com.example.cathaybankairport.ui.theme.CathayBankAirportTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CathayBankAirportTheme {
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
                                    ExchangeRatePage()
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
