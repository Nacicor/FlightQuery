package com.example.cathaybankairport.main.airportinfo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cathaybankairport.main.airportinfo.api.AirPortInfoItem


@Composable
fun AirportInfoItem(data: AirPortInfoItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .align(alignment = Alignment.CenterHorizontally)) {
            Column(Modifier.weight(1.0f)) {
                Row {
                    Column (modifier = Modifier.weight(1.0f)){
                        Text(text = "預計時間" , textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.padding(4.dp))
                        data.scheduleTime?.let { Text(text = it, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Column (modifier = Modifier.weight(1.0f)){
                        Text(text = "實際時間", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.padding(4.dp))
                        data.actualTime?.let { Text(text = it, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "航機班號 : ${data.flightNumber}", textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = "航廈/登機門 : ${data.terminal}/${data.gate}", textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = data.remark, color = colorResource(id = data.status.color) , textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth())

            }
            Column(
                Modifier.weight(1.0f)) {
                data.departureAirportID?.let { Text(text = it, textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth()) }
                Spacer(modifier = Modifier.padding(4.dp))
                data.departureAirport?.let { Text(text = it, textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth()) }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "|", textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth() )
                Spacer(modifier = Modifier.padding(8.dp))
                data.arrivalAirportID?.let { Text(text = it, textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth()) }
                Spacer(modifier = Modifier.padding(4.dp))
                data.arrivalAirport?.let { Text(text = it, textAlign = TextAlign.Center , modifier = Modifier.fillMaxWidth()) }
            }
        }
    }
}


@Preview
@Composable
private fun Test() {
    AirportInfoItem(AirPortInfoItem())
}