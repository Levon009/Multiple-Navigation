package com.example.jetpack_multiplenavigation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BackToHome(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    FloatingActionButton(
        modifier = modifier.size(45.dp),
        onClick = {
            navController.navigateUp()
        },
        containerColor = Color.Magenta,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Home",
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun LinearLoadingAnimation(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(45.dp),
            color = Color.Magenta
        )
    }
}

@Composable
fun CircularLoadingAnimation(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(45.dp),
            color = Color.Magenta
        )
    }
}