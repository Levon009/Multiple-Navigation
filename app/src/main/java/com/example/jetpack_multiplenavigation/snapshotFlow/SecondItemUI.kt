package com.example.jetpack_multiplenavigation.snapshotFlow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondItemUI(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .height(70.dp)
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(12.dp))
            .clickable { }
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}