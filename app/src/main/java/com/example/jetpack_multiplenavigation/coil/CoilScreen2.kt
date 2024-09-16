package com.example.jetpack_multiplenavigation.coil

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.jetpack_multiplenavigation.R

@Composable
fun CoilScreen2(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(350.dp)
            .height(350.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(ImageUrl)
                .size(Size.ORIGINAL)
                .scale(Scale.FILL)
                .error(R.drawable.klarna)
                .placeholder(R.drawable.paypal)
                .crossfade(true)
                .crossfade(2000)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = null,
            filterQuality = FilterQuality.High,
            onError = {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            },
            onLoading = {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            },
            loading = {
                CircularProgressIndicator()
            }
        )
    }
}