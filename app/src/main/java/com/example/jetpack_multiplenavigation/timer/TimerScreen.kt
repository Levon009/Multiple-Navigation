package com.example.jetpack_multiplenavigation.timer

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Timer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    initialValue: Float,
    strokeWidth: Dp,
    circleColor: Color,
    activeBarColor: Color,
    inactiveBarColor: Color,
    navController: NavHostController
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var currentTime by remember {
        mutableLongStateOf(totalTime)
    }
    var value by remember {
        mutableFloatStateOf(initialValue)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Timer",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                ),
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Box(contentAlignment = Alignment.Center,) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.onSizeChanged {
                        size = it
                    }
                ) {
                    Canvas(modifier = modifier) {
                        drawArc(
                            color = inactiveBarColor,
                            startAngle = -220f,
                            sweepAngle = 250f,
                            size = Size(size.width.toFloat(), size.height.toFloat()),
                            useCenter = false,
                            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                        )
                        drawArc(
                            color = activeBarColor,
                            startAngle = -220f,
                            sweepAngle = 250f * value,
                            useCenter = false,
                            size = Size(size.width.toFloat(), size.height.toFloat()),
                            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                        )
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val radius = size.width / 2f
                        val angle = (250f * value + 145f) * (PI / 180).toFloat()
                        val a = cos(angle) * radius + center.x
                        val b = sin(angle) * radius + center.y

                        drawPoints(
                            listOf(Offset(a, b)),
                            pointMode = PointMode.Points,
                            color = circleColor,
                            strokeWidth = (strokeWidth * 3f).toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                    Text(
                        text = (currentTime / 1000L).toString(),
                        color = Color.White,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = {
                            if (currentTime <= 0L) {
                                currentTime = totalTime
                                isTimerRunning = true
                            } else {
                                isTimerRunning = !isTimerRunning
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomCenter),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!isTimerRunning || currentTime <= 0L) {
                                Color.Green
                            } else {
                                Color.Red
                            }
                        )
                    ) {
                        Text(
                            text = if (isTimerRunning && currentTime >= 0L) "Stop"
                            else if (!isTimerRunning && currentTime >= 0L) "Start."
                            else "Restart"
                        )
                    }
                }
            }
        }
    }
}