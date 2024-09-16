package com.example.draf.personsListFull.dragDropListItem

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun rememberDragListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
) : DragDropListState {
    return DragDropListState(
        lazyListState = lazyListState,
        onMove = onMove
    )
}