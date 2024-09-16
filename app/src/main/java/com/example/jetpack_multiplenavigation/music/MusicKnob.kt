package com.example.jetpack_multiplenavigation.music

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import com.example.jetpack_multiplenavigation.R
import kotlin.math.PI
import kotlin.math.atan2

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitedAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember {
        mutableFloatStateOf(limitedAngle)
    }
    var centerX by remember {
        mutableFloatStateOf(0f)
    }
    var centerY by remember {
        mutableFloatStateOf(0f)
    }
    var touchX by remember {
        mutableFloatStateOf(0f)
    }
    var touchY by remember {
        mutableFloatStateOf(0f)
    }
    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitedAngle..limitedAngle) {
                            val fixedAngle = if (angle in -180f..-limitedAngle) {
                                angle + 360f
                            } else {
                                angle
                            }
                            rotation = fixedAngle
                            val percent = (fixedAngle - limitedAngle) / (360 - limitedAngle * 2f)
                            onValueChange(percent)
                            true
                        } else false
                    }

                    else -> false
                }
            }
            .rotate(rotation)
    )
}