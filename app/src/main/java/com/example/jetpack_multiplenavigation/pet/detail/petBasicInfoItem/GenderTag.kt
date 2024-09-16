package com.example.jetpack_multiplenavigation.pet.detail.petBasicInfoItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenderTag(
    modifier: Modifier = Modifier,
    gender: String
) {
    val color = if (gender == "Male") Color.Blue else Color.Red
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(.2f))
    ) {
        Text(
            text = gender,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp, 4.dp, 12.dp, 6.dp)
        )
    }
}