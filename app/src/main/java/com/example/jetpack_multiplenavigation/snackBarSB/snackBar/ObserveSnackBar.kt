package com.example.jetpack_multiplenavigation.snackBarSB.snackBar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ObserveSnackBar(snackBarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    ObserveAsEventSB(
        flow = SnackBarController.eventsChannel,
        snackBarHostState
    ) { eventsChannel ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()

            val result = snackBarHostState.showSnackbar(
                message = eventsChannel.message,
                actionLabel = eventsChannel.action?.name,
                duration = SnackbarDuration.Long
            )

            when(result) {
                SnackbarResult.ActionPerformed -> {
                    eventsChannel.action?.action?.invoke()
                }
                SnackbarResult.Dismissed -> {
                    snackBarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }
}