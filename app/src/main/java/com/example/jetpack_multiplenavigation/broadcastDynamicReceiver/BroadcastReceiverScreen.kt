package com.example.jetpack_multiplenavigation.broadcastDynamicReceiver

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BroadcastReceiverScreen(navController: NavHostController) {
    val context = LocalContext.current
    val testReceiver = remember {
        mutableStateOf(TestReceiver())
    }.value
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        registerTestReceiver(
            context = context,
            testReceiver = testReceiver,
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        unregisterTestReceiver(
                            context = context,
                            testReceiver = testReceiver
                        )
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Button(onClick = {
                context.sendBroadcast(
                    Intent("ACTION_TEST")
                )
            }) {
                Text(
                    text = "Send broadcast",
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnspecifiedRegisterReceiverFlag")
private fun registerTestReceiver(
    context: Context,
    testReceiver: TestReceiver
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.registerReceiver(
            testReceiver,
            IntentFilter("ACTION_TEST"),
            Context.RECEIVER_EXPORTED
        )
    }
}

private fun unregisterTestReceiver(
    context: Context,
    testReceiver: TestReceiver
) {
    context.unregisterReceiver(testReceiver)
}