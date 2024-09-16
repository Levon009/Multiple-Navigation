package com.example.jetpack_multiplenavigation.chat.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.chat.presentation.ChatViewModel
import com.example.jetpack_multiplenavigation.permissions.Permissions
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatFcmScreen(navController: NavHostController) {
    val context = LocalContext.current
    checkForPermission(context)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Firebase chat",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Refresh", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            val chatViewModel = koinViewModel<ChatViewModel>()
            val state = chatViewModel.state

            if (state.isEnteringToken) {
                SubmitTokenDialog(
                    localToken = state.remoteToken,
                    onTokenChange = chatViewModel::onRemoteTokenChange,
                    onSubmit = chatViewModel::onSubmitRemoteToken
                )
            } else {
                MessagingScreen(
                    messageText = state.messageText,
                    onMessageChange = chatViewModel::onMessageChange,
                    onMessageSend = {
                        chatViewModel.sendMessage(isBroadcast = false)
                    },
                    onMessageBroadcast = {
                        chatViewModel.sendMessage(isBroadcast = true)
                    }
                )
            }
        }
    }
}

fun checkForPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) ==  PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            ActivityCompat.requestPermissions(
                (context as Activity),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                Permissions.POST_NOTIFICATION_R_CODE
            )
        }
    }
}