package com.example.jetpack_multiplenavigation.bindedServiceTimer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

private var timerBinder: TimerService.TimerBinder? = null
private var mBound = false

private val timerServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        timerBinder = service as TimerService.TimerBinder
        mBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mBound = false
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerServiceScreen(navController: NavHostController) {
    val context = LocalContext.current

    Intent(context.applicationContext, TimerService::class.java).apply {
        context.bindService(this, timerServiceConnection, Context.BIND_AUTO_CREATE)
    }

    val timerViewModel = koinViewModel<TimerViewModel>()
    if (timerBinder != null) {
        LaunchedEffect(key1 = timerBinder?.timerChannel) {
            timerBinder?.timerChannel?.collect { i ->
                timerViewModel.updateCounter(i)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Timer",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        context.unbindService(timerServiceConnection)
                        mBound = false
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            Text(
                text = timerViewModel.counter.toString(),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(45.dp))
            Button(
                onClick = {
                    if (mBound) {
                        if (timerViewModel.counter == 0) {
                            timerBinder?.start()
                        } else {
                            timerBinder?.restart()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (timerViewModel.counter == 0) Color.Green else Color.Red
                )
            ) {
                Text(
                    text = if (timerViewModel.counter == 0) "Start" else "Restart",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (mBound) {
                        if (timerViewModel.counter > 0) {
                            timerBinder?.pause()
                            timerViewModel.updatePaused()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (timerViewModel.isPaused) Color.Green else Color.Red
                )
            ) {
                Text(
                    text = if (timerViewModel.isPaused) "Start" else "Pause",
                    fontSize = 22.sp,
                    color = if (timerViewModel.isPaused) Color.Black else Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}