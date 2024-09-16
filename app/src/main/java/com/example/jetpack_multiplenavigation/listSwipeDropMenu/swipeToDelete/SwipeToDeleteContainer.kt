package com.example.jetpack_multiplenavigation.listSwipeDropMenu.swipeToDelete

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.data.ProgrammingLanguage
import kotlinx.coroutines.delay

@Composable
fun SwipeToDeleteContainer(
    programmingLanguage: ProgrammingLanguage,
    animDuration: Int = 500,
    onDelete: (ProgrammingLanguage) -> Unit,
    content: @Composable (ProgrammingLanguage) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { swipeToDismissBoxValue ->
            if (swipeToDismissBoxValue == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
                true
            } else false
        }
    )
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animDuration.toLong())
            onDelete(programmingLanguage)
        }
    }
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                DeleteBackground(dismissState = dismissState)
            },
            enableDismissFromEndToStart = true,
            enableDismissFromStartToEnd = false
        ) {
            content(programmingLanguage)
        }
    }
}