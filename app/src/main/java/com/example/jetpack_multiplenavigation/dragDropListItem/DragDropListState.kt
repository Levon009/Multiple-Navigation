package com.example.jetpack_multiplenavigation.dragDropListItem

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.Job

class DragDropListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggedDistance by mutableFloatStateOf(0f)
    var initiallyDraggedItem by mutableStateOf<LazyListItemInfo?>(null)
    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)
    var overScrollJob by mutableStateOf<Job?>(null)
    val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(absolute = it)
        }
    val initialOffset: Pair<Int, Int>?
        get() = initiallyDraggedItem?.let {
            Pair(it.offset, it.offsetEnd)
        }
    val itemDisplacement: Float?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(absolute = it)
        } ?.let {
            item -> (initiallyDraggedItem?.offset ?: 0f).toFloat() + draggedDistance - item.offset
        }

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { item ->
            offset.y.toInt() in item.offset..(item.offset + item.size)
        } ?. also {
            initiallyDraggedItem = it
            currentIndexOfDraggedItem = it.index
        }
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y

        initialOffset?.let {  (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentElement?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo.filterNot { item ->
                    item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index
                } .firstOrNull { item ->
                    val delta = startOffset - hovered.offsetEnd
                    when {
                        delta > 0 -> (endOffset > item.offsetEnd)
                        else -> (startOffset < item.offset)
                    }
                } ?. also { item ->
                    currentIndexOfDraggedItem?.let { current ->
                        onMove.invoke(current, item.index)
                    }
                    currentIndexOfDraggedItem = item.index
                }
            }
        }
    }

    fun onDragInterrupted() {
        draggedDistance = 0f
        initiallyDraggedItem = null
        currentIndexOfDraggedItem = null
        overScrollJob?.cancel()
    }

    fun checkForOverScroll() : Float {
        return initiallyDraggedItem?.let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance

            return@let when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff ->
                    diff > 0
                }
                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff ->
                    diff < 0
                }
                else -> null
            }
        } ?: 0f
    }
}

@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
) : DragDropListState {
    return DragDropListState(
        lazyListState = lazyListState,
        onMove = onMove
    )
}