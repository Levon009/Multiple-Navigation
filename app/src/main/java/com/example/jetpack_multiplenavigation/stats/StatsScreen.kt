package com.example.jetpack_multiplenavigation.stats

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun StaticBar(
    percentage: Float,
    num: Int,
    statName: String,
    strokeWidth: Dp = 8.dp,
    fontSize: TextUnit = 20.sp,
    color: Color,
    animDuration: Int = 4000,
    animDelay: Int = 1500
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )
    LaunchedEffect(key1 = animationPlayed) {
        animationPlayed = true
    }
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(strokeWidth * 3f)
            .padding(horizontal = 20.dp)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            ) {
                Text(
                    text = statName,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${(currPercentage.absoluteValue * num).toInt()}%",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = color,
                    topLeft = Offset(width / 10f, height / 2f),
                    size = Size((currPercentage.absoluteValue * (width - (width / 10f))), strokeWidth.toPx()),
                    cornerRadius = CornerRadius(15f),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Stats(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Stats",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
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
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                StaticBar(
                    percentage = 1f,
                    num = 100,
                    statName = "Stat 1",
                    color = Color.Green
                )
                Spacer(modifier = Modifier.height(30.dp))
                StaticBar(
                    percentage = 0.8f,
                    num = 100,
                    statName = "Stat 2",
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(30.dp))
                StaticBar(
                    percentage = 0.3f,
                    num = 100,
                    statName = "Stat 3",
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.height(30.dp))
                StaticBar(
                    percentage = 0.6f,
                    num = 100,
                    statName = "Stat 4",
                    color = Color.Blue
                )
            }
        }
    }
}