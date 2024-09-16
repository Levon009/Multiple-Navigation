package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.R

@Composable
fun getPosts() : List<Painter> {
    return listOf(
        painterResource(id = R.drawable.kmm),
        painterResource(id = R.drawable.intermediate_dev),
        painterResource(id = R.drawable.master_logical_thinking),
        painterResource(id = R.drawable.bad_habits),
        painterResource(id = R.drawable.multiple_languages),
        painterResource(id = R.drawable.learn_coding_fast),
        painterResource(id = R.drawable.kmm),
        painterResource(id = R.drawable.intermediate_dev),
        painterResource(id = R.drawable.master_logical_thinking),
        painterResource(id = R.drawable.bad_habits),
        painterResource(id = R.drawable.multiple_languages),
        painterResource(id = R.drawable.learn_coding_fast)
    )
}

@Composable
fun PostsSection(
    modifier: Modifier = Modifier,
    posts: List<Painter>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.scale(1.01f)
    ) {
        items(posts.size) { index ->
            Image(
                painter = posts[index],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}