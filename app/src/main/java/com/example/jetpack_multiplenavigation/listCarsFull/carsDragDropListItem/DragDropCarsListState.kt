package com.example.jetpack_multiplenavigation.listCarsFull.carsDragDropListItem

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import com.example.jetpack_multiplenavigation.personsListFull.dragDropListItem.getVisibleListItemInfo
import com.example.jetpack_multiplenavigation.personsListFull.dragDropListItem.offsetEnd
import kotlinx.coroutines.Job

class DragDropCarsListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggedDistance by mutableFloatStateOf(0f)
    var draggedItem by mutableStateOf<LazyListItemInfo?>(null)
    var draggedItemIndex by mutableStateOf<Int?>(null)
    var overScrollJob by mutableStateOf<Job?>(null)
    val currentItem: LazyListItemInfo?
        get() = draggedItemIndex?.let { index ->
            lazyListState.getVisibleListItemInfo(absolute = index)
        }
    val initialOffset: Pair<Int, Int>?
        get() = draggedItem?.let { item ->
            Pair(item.offset, item.offsetEnd)
        }
    val itemDisplacement: Float?
        get() = draggedItemIndex?.let { indeex ->
            lazyListState.getVisibleListItemInfo(absolute = indeex)
        } ?.let {
            item -> (draggedItem?.offset ?: 0f).toFloat() + draggedDistance - item.offset
        }

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { item ->
            offset.y.toInt() in item.offset..(item.offset + item.size)
        } ?. also { item ->
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
                    endOffset < item.offset || startOffset > item.offsetEnd || hovered.index == item.index
                }. firstOrNull { item ->
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
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff->
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