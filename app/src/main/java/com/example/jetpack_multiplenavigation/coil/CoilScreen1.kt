package com.example.jetpack_multiplenavigation.coil

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.jetpack_multiplenavigation.R
import kotlin.math.min

@Composable
fun CoilScreen1(modifier: Modifier = Modifier) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(ImageUrl)
            .size(Size.ORIGINAL)
            .error(R.drawable.klarna)
            .placeholder(R.drawable.paypal)
            .transformations(CircleCropTransformation())
            .crossfade(true)
            .crossfade(2000)
            .scale(Scale.FILL)
            .build()
    ).state
    val transition by animateFloatAsState(
        targetValue = if (imageState is AsyncImagePainter.State.Success) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2500,
            delayMillis = 500
        ),
        label = ""
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(350.dp)
            .width(350.dp)
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            CircleCropTransformation()
        }
        if (imageState is AsyncImagePainter.State.Loading) {
            CircleCropTransformation()
        }
        if (imageState is AsyncImagePainter.State.Success) {
            imageState.painter.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .alpha(transition)
                        .scale(.8f + (.2f * transition))
                        .graphicsLayer {
                            rotationX = (1f - transition) * 5f
                        }
                        .alpha(min(1f, (transition / .2f)))
                )
            }
        }
    }
}