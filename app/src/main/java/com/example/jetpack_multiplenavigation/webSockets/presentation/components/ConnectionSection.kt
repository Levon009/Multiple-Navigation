package com.example.jetpack_multiplenavigation.webSockets.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.webSockets.data.listeners.WebSocketsListener
import com.example.jetpack_multiplenavigation.webSockets.data.request.WebSocketsRequest
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.WebSocket

@Composable
fun ConnectionSection(
    lazyListState: LazyListState,
    lastIndex: Int,
    okHttpClient: OkHttpClient,
    webSocketsListener: WebSocketsListener,
    webSocket: MutableState<WebSocket?>
) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ElevatedButton(
            onClick = {
                webSocket.value = okHttpClient.newWebSocket(WebSocketsRequest.request, webSocketsListener)
                scope.launch {
                    if (lastIndex > 0) lazyListState.animateScrollToItem(lastIndex)
                }
            },
            elevation = ButtonDefaults.buttonElevation(25.dp)
        ) {
            Text(
                text = "Connect",
                fontFamily = FontFamily.Serif
            )
        }
        ElevatedButton(
            onClick = {
                webSocket.value?.close(1000, "Cancelled, manually.")
            },
            elevation = ButtonDefaults.buttonElevation(25.dp)
        ) {
            Text(
                text = "Disconnect",
                fontFamily = FontFamily.Serif
            )
        }
    }
}