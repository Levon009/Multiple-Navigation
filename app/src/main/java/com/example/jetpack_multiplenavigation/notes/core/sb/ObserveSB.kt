package com.example.jetpack_multiplenavigation.notes.core.sb

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ObserveSB(snackBarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    ObserveSBEvents(
        flow = SBController.eventChannel,
        snackBarHostState
    ) { eventChannel ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()

            val result = snackBarHostState.showSnackbar(
                message = eventChannel.message,
                actionLabel = eventChannel.action?.name,
                duration = eventChannel.duration
            )

            when(result) {
                SnackbarResult.Dismissed -> {
                    snackBarHostState.currentSnackbarData?.dismiss()
                }
                SnackbarResult.ActionPerformed -> {
                    eventChannel.action?.action?.invoke()
                }
            }
        }
    }
}