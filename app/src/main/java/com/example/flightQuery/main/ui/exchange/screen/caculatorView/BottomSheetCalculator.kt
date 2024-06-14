package com.example.flightQuery.main.ui.exchange.screen.caculatorView

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightQuery.R
import com.example.flightQuery.main.ui.exchange.ExchangeRateViewModel

@Composable
fun BottomSheetCalculator(
    currency: String,
    model: ExchangeRateViewModel
) {
    var enterAmountString by remember {
        mutableStateOf(model.displayList.value[currency].toString())
    }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = if (isSystemInDarkTheme()) colorResource(id = R.color.DarkGrey) else colorResource(
                    id = R.color.lightGray
                )
            )
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.align(alignment = Alignment.TopCenter)) {
            Text(
                text = currency.uppercase(), modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), textAlign = TextAlign.Center, fontSize = 20.sp
            )
            Text(
                text = enterAmountString,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Row(Modifier.align(Alignment.CenterHorizontally)) {
                for (i in 7..9) {
                    CalculatorButton(title = i.toString()) {
                        enterAmountString += i
                        enterAmountString = removeLeadingZeros(enterAmountString)
                        model.updateCurrencyAmounts(
                            currency,
                            enterAmountString.toDouble(),
                        )
                    }
                }
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                for (i in 4..6) {
                    CalculatorButton(title = i.toString()) {
                        enterAmountString += i
                        enterAmountString = removeLeadingZeros(enterAmountString)
                        model.updateCurrencyAmounts(
                            currency,
                            enterAmountString.toDouble(),
                        )
                    }
                }
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                for (i in 1..3) {
                    CalculatorButton(title = i.toString()) {
                        enterAmountString += i
                        enterAmountString = removeLeadingZeros(enterAmountString)
                        model.updateCurrencyAmounts(
                            currency,
                            enterAmountString.toDouble(),
                        )
                    }
                }
            }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                CalculatorButton(title = "0") {
                    enterAmountString += "0"
                    enterAmountString = removeLeadingZeros(enterAmountString)
                    model.updateCurrencyAmounts(
                        currency,
                        enterAmountString.toDouble(),
                    )
                }
                CalculatorButton(title = ".") {
                    if (!enterAmountString.contains("."))
                        enterAmountString += "."
                }

                CalculatorButton(title = "C") {
                    enterAmountString = "0"
                    model.updateCurrencyAmounts(
                        currency,
                        enterAmountString.toDouble()
                    )
                }
            }
        }
    }
}

fun removeLeadingZeros(input: String): String {
    val firstNonZeroIndex = input.indexOfFirst { it != '0' }
    return if (firstNonZeroIndex != -1) {
        input.substring(firstNonZeroIndex)
    } else {
        "0"
    }
}

