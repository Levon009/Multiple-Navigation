package com.example.jetpack_multiplenavigation.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.ui.theme.ButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.TextWhite

@Composable
fun MeditationSection(color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Meditation thoughts",
                color = TextWhite,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Meditation daily ~ 3-10 minute.",
                color = TextWhite,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .clickable { }
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
            )
        }
    }
}