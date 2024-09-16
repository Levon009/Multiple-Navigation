package com.example.jetpack_multiplenavigation.students.swipeToDeleteStudentItem

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
import com.example.draf.students.data.model.Student
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.delay

@Composable
fun SwipeToRefreshStudentContainer(
    student: Student,
    animDuration: Int = 700,
    onRemove: (Student) -> Unit,
    content: @Composable (Student) -> Unit
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
            onRemove(student)
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
                DeleteStudentBackground(dismissState = dismissState)
            },
            enableDismissFromStartToEnd = false,
            enableDismissFromEndToStart = true
        ) {
            content(student)
        }
    }
}