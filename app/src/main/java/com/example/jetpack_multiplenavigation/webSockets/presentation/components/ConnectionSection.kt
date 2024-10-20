package com.example.jetpack_multiplenavigation.webSockets.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.webSockets.data.listeners.WebSocketsListener
import com.example.jetpack_multiplenavigation.webSockets.data.request.WebSocketsRequest
import okhttp3.OkHttpClient
import okhttp3.WebSocket

@Composable
fun ConnectionSection(
    okHttpClient: OkHttpClient,
    webSocketsListener: WebSocketsListener,
    webSocket: MutableState<WebSocket?>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ElevatedButton(
            onClick = {
                webSocket.value = okHttpClient.newWebSocket(WebSocketsRequest.request, webSocketsListener)
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