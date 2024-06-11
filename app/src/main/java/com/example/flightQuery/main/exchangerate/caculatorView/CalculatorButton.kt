package com.example.flightQuery.main.exchangerate.caculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightQuery.R

@Composable
fun CalculatorButton(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = if (isSystemInDarkTheme()) colorResource(id = R.color.DarkGrey) else Color.White)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.lightGrayLittle),
                shape = RoundedCornerShape(8.dp)
            )
            .size(64.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.align(alignment = Alignment.Center),
        )

    }
}
