package com.example.jetpack_multiplenavigation.personsListFull.dragDropListItem

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
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
    private var draggedDistance by mutableFloatStateOf(0f)
    private var draggedItem by mutableStateOf<LazyListItemInfo?>(null)
    var draggedItemIndex by mutableStateOf<Int?>(null)
    private var overScrollJob by mutableStateOf<Job?>(null)
    private val currentItem: LazyListItemInfo?
        get() = draggedItemIndex?.let {
            lazyListState.getVisibleListItemInfo(absolute = it)
        }
    private val initialOffset: Pair<Int, Int>?
        get() = draggedItem?.let { item ->
            Pair(item.offset, item.offsetEnd)
        }
    val itemDisplacement: Float?
        get() = draggedItemIndex?.let {
            lazyListState.getVisibleListItemInfo(absolute = it)
        } ?.let {
            item -> (draggedItem?.offset ?: 0f).toFloat() + draggedDistance - item.offset
        }

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { item ->
            offset.y.toInt() in item.offset..(item.offset + item.size)
        } ?.also {  item ->
            draggedItem = item
            draggedItemIndex = item.index
        }
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y

        initialOffset?.let { (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentItem?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo.filterNot { item ->
                    startOffset > item.offsetEnd || endOffset < item.offset || hovered.index == item.index
                }.firstOrNull { item ->
                    val delta = startOffset - hovered.offsetEnd
                    when {
                        delta > 0 -> (endOffset > item.offsetEnd)
                        else -> (startOffset < item.offset)
                    }
                } ?. also { item ->
                    draggedItemIndex?.let { current ->
                        onMove.invoke(current, item.index)
                    }
                    draggedItemIndex = item.index
                }
            }
        }
    }

    fun onDragInterrupt() {
        draggedDistance = 0f
        draggedItem = null
        draggedItemIndex = null
        overScrollJob?.cancel()
    }

    fun checkForOverScroll() : Float {
        return draggedItem?.let { item ->
            val startOffset = draggedDistance + item.offset
            val endOffset = draggedDistance + item.offsetEnd

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