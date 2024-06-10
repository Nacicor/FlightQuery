package com.example.cathaybankairport.main.exchangerate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cathaybankairport.R
import com.example.cathaybankairport.main.exchangerate.api.RateInfoItem
import com.example.cathaybankairport.main.exchangerate.caculator.BottomSheetCalculator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRatePage() {
    val allRateName = RateInfoItem.Data.getCurrencies()
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val model = remember {
        ExchangeRateViewModel()
    }
    var selectItemName by remember {
        mutableStateOf("AUD")
    }
    val displayList = model.displayList.collectAsState()
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(allRateName) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .clickable {
                        showBottomSheet = true
                        selectItemName = it
                    },
                colors = CardDefaults.cardColors(colorResource(id = R.color.lightBlue)),
                elevation = CardDefaults.cardElevation(8.dp),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = it.uppercase(),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp
                    )
                    Text(
                        text = displayList.value[it.lowercase().toString()].toString(),
                        Modifier
                            .weight(1.0f)
                            .padding(8.dp),
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.padding(32.dp),
            sheetState = bottomSheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            BottomSheetCalculator(selectItemName, model)
        }
    }
}

