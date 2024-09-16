package com.example.jetpack_multiplenavigation.bottomTaskBar

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomDropDown(
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    var sizeState by remember {
        mutableStateOf(0.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(size)
                .background(Color.Green)
                .clickable {
                    sizeState -= 50.dp
                    scope.launch {
                        delay(1500L)
                        val result = snackBarHostState.showSnackbar(
                            message = "Hello World",
                            actionLabel = "Action",
                            duration = SnackbarDuration.Short
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Toast.makeText(context, "Wish you to have a nice day.", Toast.LENGTH_SHORT).show()
                            }
                            SnackbarResult.Dismissed -> {}
                        }
                    }
                }
        ) {
            MenuItem_1()
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Blue)
                .clickable {
                    if (sizeState < 50.dp)
                        sizeState += 50.dp
                }
        ) {
            MenuItem_2()
        }
    }
}