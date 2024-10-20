package com.example.jetpack_multiplenavigation.webSockets.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_multiplenavigation.ui.theme.BlueViolet3
import com.example.jetpack_multiplenavigation.webSockets.presentation.messages.MessageState

@Composable
fun ListSection(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    state: MessageState
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        items(state.messages) { message ->
            Row(
                horizontalArrangement = if (message.isOwner) Arrangement.End else Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(6.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                if (message.isOwner) BlueViolet3.copy(alpha = 0.6f)
                                else Color.LightGray.copy(alpha = 0.4f)
                            )
                    ) {
                        Text(
                            text = message.date,
                            color = if (message.isOwner) Color.White else Color.DarkGray,
                            fontFamily = FontFamily.Serif,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        Text(
                            text = message.text,
                            color = if (message.isOwner) Color.White else Color.DarkGray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .padding(6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}