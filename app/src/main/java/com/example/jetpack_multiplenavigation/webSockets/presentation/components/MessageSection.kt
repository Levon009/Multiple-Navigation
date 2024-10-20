package com.example.jetpack_multiplenavigation.webSockets.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.ui.theme.ButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen3
import com.example.jetpack_multiplenavigation.webSockets.presentation.WebSocketsViewModel
import okhttp3.WebSocket

@Composable
fun MessageSection(
    text: MutableState<String>,
    keyboard: SoftwareKeyboardController,
    webSocket: WebSocket?,
    viewModel: WebSocketsViewModel,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily.Serif
            ),
            placeholder = {
                Text(
                    text = "Message",
                    fontFamily = FontFamily.Serif
                )
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Image",
                    tint = LightGreen3
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboard.hide()
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Red,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(6.dp))
        IconButton(onClick = {
            if (text.value.isNotEmpty()) {
                webSocket?.send(text.value)
                viewModel.setMessage(Pair(true, text.value))
                text.value = ""
            }
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Send,
                contentDescription = "Send",
                tint = ButtonBlue,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp)
            )
        }
    }
}