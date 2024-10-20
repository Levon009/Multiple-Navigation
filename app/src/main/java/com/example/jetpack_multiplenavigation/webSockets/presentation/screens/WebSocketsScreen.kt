package com.example.jetpack_multiplenavigation.webSockets.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.webSockets.data.listeners.WebSocketsListener
import com.example.jetpack_multiplenavigation.webSockets.presentation.WebSocketsViewModel
import com.example.jetpack_multiplenavigation.webSockets.presentation.components.ConnectionSection
import com.example.jetpack_multiplenavigation.webSockets.presentation.components.ListSection
import com.example.jetpack_multiplenavigation.webSockets.presentation.components.MessageSection
import com.example.jetpack_multiplenavigation.webSockets.presentation.messages.MessagesEvents
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebSocketsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel = koinViewModel<WebSocketsViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val webSocketsListener = WebSocketsListener(viewModel)
    val okHttpClient = OkHttpClient()
    val webSocket = rememberSaveable {
        mutableStateOf<WebSocket?>(null)
    }
    val text = remember {
        mutableStateOf("")
    }
    var connected by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        viewModel.socketStatus.collectLatest {
            connected = if (it) "Connected" else "Disconnected"
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.message.collectLatest {
            if (message != it.second) {
                viewModel.onEvent(MessagesEvents.SaveMessage(
                    MessageDto(
                        text = it.second,
                        isOwner = it.first,
                        owner = if (it.first) "Me: " else "Other: ",
                        favorite = false,
                        timestamp = System.currentTimeMillis(),
                        date = Date().toString().slice(0..9)
                    )
                ))
                message = it.second
            }
        }
    }

    LaunchedEffect(key1 = message) {
        if (message.isNotEmpty()) {
            delay(400)
            message = ""
            lazyListState.scrollToItem(state.value.messages.lastIndex)
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
                lazyListState = lazyListState,
                lastIndex = state.value.messages.lastIndex,
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
                lazyListState = lazyListState,
                state = state.value,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            MessageSection(
                text = text,
                keyboard = keyboard!!,
                webSocket = webSocket.value,
                viewModel = viewModel
            )
        }
    }
}
