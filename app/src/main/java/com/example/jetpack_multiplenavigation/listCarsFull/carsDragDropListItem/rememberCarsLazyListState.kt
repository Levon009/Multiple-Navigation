package com.example.jetpack_multiplenavigation.listCarsFull.carsDragDropListItem

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun rememberCarsLazyListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
) : DragDropCarsListState {
    return DragDropCarsListState(
        lazyListState = lazyListState,
        onMove = onMove
    )
}