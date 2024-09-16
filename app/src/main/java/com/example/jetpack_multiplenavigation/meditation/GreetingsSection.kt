package com.example.jetpack_multiplenavigation.meditation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.ui.theme.TextWhite

@Composable
fun GreetingsSection(
    name: String,
    onSearchClick: () -> Unit
) {
    val fontFamily = FontFamily(Font(R.font.gothica1_black, FontWeight.Thin))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = "Good morning $name",
                color = TextWhite,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Wish you to have a nice day.",
                color = TextWhite,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onSearchClick()
                }
        )
    }
}