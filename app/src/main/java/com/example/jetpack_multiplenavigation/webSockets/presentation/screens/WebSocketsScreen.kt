package com.example.jetpack_multiplenavigation.webSockets.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.ui.theme.BlueViolet3
import com.example.jetpack_multiplenavigation.ui.theme.ButtonBlue
import com.example.jetpack_multiplenavigation.ui.theme.LightGreen3
import com.example.jetpack_multiplenavigation.webSockets.data.listeners.WebSocketsListener
import com.example.jetpack_multiplenavigation.webSockets.data.request.WebSocketsRequest
import com.example.jetpack_multiplenavigation.webSockets.presentation.WebSocketsViewModel
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.flow.collectLatest
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebSocketsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel = koinViewModel<WebSocketsViewModel>()
    val messages = viewModel.messages.collectAsStateWithLifecycle()
    val webSocketsListener = WebSocketsListener(viewModel)
    val okHttpClient = OkHttpClient()
    val webSocket = rememberSaveable {
        mutableStateOf<WebSocket?>(null)
    }
    val text = remember {
        mutableStateOf("")
    }
    val owner = remember {
        mutableStateOf("")
    }
    val isOwner = remember {
        mutableStateOf(false)
    }
    val message = remember {
        mutableStateOf("")
    }
    var connected by remember {
        mutableStateOf("")
    }

    var s by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        viewModel.socketStatus.collectLatest {
            connected = if (it) "Connected" else "Disconnected"
        }
    }
    val counter = rememberSaveable {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        viewModel.message.collectLatest {
            counter.value++
            owner.value = if (it.first) "Me: " else "Other: "
            message.value = it.second
            isOwner.value = it.first

            messages.value.add(
                MessageDto(
                    text = message.value,
                    isOwner = isOwner.value,
                    owner = owner.value,
                    favorite = false,
                    timestamp = System.currentTimeMillis(),
                    date = Date().toString().slice(0..9)
                )
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ConnectionSection(
                okHttpClient = okHttpClient,
                webSocketsListener = webSocketsListener,
                webSocket = webSocket
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = connected,
                color = if (connected == "Connected") Color.Green else Color.Red,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(16.dp))

            ListSection(
                viewModel = viewModel,
                modifier = Modifier.weight(1f),
                message = message
            )

            Spacer(modifier = Modifier.height(16.dp))

            MessageSection(
                text = text,
                keyboard = keyboard!!,
                webSocket = webSocket.value,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun ConnectionSection(
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

@Composable
private fun ListSection(
    modifier: Modifier = Modifier,
    viewModel: WebSocketsViewModel,
    message: MutableState<String>
) {
    val m = message.value
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        items(viewModel.messages.value) { message ->
            Row(
                horizontalArrangement = if (message.isOwner) Arrangement.End else Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
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

@Composable
private fun MessageSection(
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
