package com.example.jetpack_multiplenavigation.simpleAnimation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SimpleAnimation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        Animation3()
        TextButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(25.dp),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Return",
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}