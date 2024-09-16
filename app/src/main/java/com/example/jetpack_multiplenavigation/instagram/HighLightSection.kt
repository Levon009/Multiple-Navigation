package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R

@Composable
fun getHighLights() : List<ImageWithText> {
    return listOf(
        ImageWithText(
            image = painterResource(id = R.drawable.youtube),
            text = "YouTube"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.qa),
            text = "Q&A"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.discord),
            text = "Discord"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.telegram),
            text = "Telegram"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.youtube),
            text = "YouTube 2"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.qa),
            text = "Q&A 2"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.discord),
            text = "Discord 2"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.telegram),
            text = "Telegram 2"
        )
    )
}

@Composable
fun HighLightSection(
    modifier: Modifier = Modifier,
    highLights: List<ImageWithText>
) {
    LazyRow(modifier = modifier) {
        items(highLights.size) { index ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .clickable { }
                ) {
                    RoundImage(
                        painter = highLights[index].image,
                        modifier = Modifier.size(60.dp)
                    )
                }
                Text(
                    text = highLights[index].text,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}