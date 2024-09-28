package com.example.jetpack_multiplenavigation.scrollToTopButton

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    state: LazyListState,
    isEnabled: Boolean = true
) {
    val scope = rememberCoroutineScope()
    val job = remember {
        mutableStateOf<Job?>(null)
    }
    val showScrollToTopButton by remember(isEnabled) {
        derivedStateOf {
            state.firstVisibleItemIndex >= 3 && isEnabled
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            job.value?.cancel()
        }
    }

    if (showScrollToTopButton) {
        FloatingActionButton(onClick = {
            scope.launch {
                state.animateScrollToItem(0)
            }
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Arrow up"
            )
        }
    }
}