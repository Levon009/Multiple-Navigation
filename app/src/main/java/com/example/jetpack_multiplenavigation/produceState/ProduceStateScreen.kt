package com.example.jetpack_multiplenavigation.produceState

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProduceStateScreen(navController: NavHostController) {
    val context = LocalContext.current
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            val counter = produceState(initialValue = 0) {
                delay(3500L)
                value = 5
            }
            if (counter.value > 0 && counter.value % 5 == 0) {
                LaunchedEffect(key1 = snackBarHostState) {
                    val result = snackBarHostState.showSnackbar(
                        message = "Hello World",
                        actionLabel = "Action",
                        duration = SnackbarDuration.Short
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            Toast.makeText(context, "Produce", Toast.LENGTH_SHORT).show()
                        }
                        SnackbarResult.Dismissed -> {}
                    }
                }
            }
            Button(
                onClick = {},
                modifier = Modifier.size(200.dp)
            ) {
                Text(text = "Click me: ${counter.value}")
            }
        }
    }
}