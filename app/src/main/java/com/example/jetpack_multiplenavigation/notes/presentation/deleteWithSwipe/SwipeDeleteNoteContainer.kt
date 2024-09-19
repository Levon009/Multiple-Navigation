package com.example.jetpack_multiplenavigation.notes.presentation.deleteWithSwipe

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
import com.example.jetpack_multiplenavigation.notes.domain.model.Note
import kotlinx.coroutines.delay

@Composable
fun SwipeDeleteNoteContainer(
    note: Note,
    animDuration: Int = 700,
    onRemove: (Note) -> Unit,
    content: @Composable (Note) -> Unit
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
            onRemove(note)
        }
    }
    AnimatedVisibility(
        visible = !isRemoved,
        exit = fadeOut() + shrinkVertically(
            animationSpec = tween(durationMillis = animDuration),
            shrinkTowards = Alignment.Top
        )
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                SwipeDeleteBG(dismissState = dismissState)
            },
            enableDismissFromEndToStart = true,
            enableDismissFromStartToEnd = false
        ) {
            content(note)
        }
    }
}