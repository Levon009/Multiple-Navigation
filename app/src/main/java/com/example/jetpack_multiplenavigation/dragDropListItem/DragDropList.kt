package com.example.jetpack_multiplenavigation.dragDropListItem

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun DragDropList(
    modifier: Modifier = Modifier,
    items: List<String>,
    onMove: (Int, Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val dragDropListState = rememberDragDropListState(onMove = onMove)
    var overScrollJob by remember {
        mutableStateOf<Job?>(null)
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { offset ->
                        dragDropListState.onDragStart(offset)
                    },
                    onDrag = { change, offset ->
                        change.consume()
                        dragDropListState.onDrag(offset = offset)

                        if (overScrollJob?.isActive == true) {
                            return@detectDragGesturesAfterLongPress
                        }

                        dragDropListState
                            .checkForOverScroll()
                            .takeIf { it != 0f }
                            ?.let {
                                overScrollJob = scope.launch {
                                    dragDropListState.lazyListState.scrollBy(it)
                                }
                            } ?: kotlin.run { overScrollJob?.cancel() }
                    },
                    onDragCancel = {
                        dragDropListState.onDragInterrupted()
                    },
                    onDragEnd = {
                        dragDropListState.onDragInterrupted()
                    }
                )
            }
            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
        state = dragDropListState.lazyListState
    ) {
        itemsIndexed(items) { index, item ->
            ListItemUI(
                text = item,
                index = index,
                dragDropListState = dragDropListState
            )
        }
    }
}