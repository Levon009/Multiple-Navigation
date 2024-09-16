package com.example.jetpack_multiplenavigation.loading

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    percentage: Float,
    num: Int,
    radius: Dp = 50.dp,
    strokeWidth: Dp = 8.dp,
    fontSize: TextUnit = 28.sp,
    color: Color = Color.Green,
    animDuration: Int = 3000,
    animDelay: Int = 0,
    nanController: NavHostController
) {
    var switcher by remember {
        mutableIntStateOf(0)
    }
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )
    LaunchedEffect(key1 = switcher) {
        if (switcher == 0) {
            delay(3500L)
            animationPlayed = true
            switcher = 1
        } else {
            delay(3500L)
            animationPlayed = false
            switcher = 0
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Loading Screen",
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            nanController.navigateUp()
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
                .padding(paddingValues)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(radius * 2f)
            ) {
                Canvas(modifier = Modifier.size(radius * 2f)) {
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = 360 * currPercentage.absoluteValue,
                        useCenter = false,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
                Text(
                    text = (num * currPercentage.absoluteValue).toInt().toString(),
                    fontSize = fontSize,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
