package com.example.jetpack_multiplenavigation.students.studentsDragDropListItem

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import com.example.draf.personsListFull.dragDropListItem.getVisibleListItemInfo
import com.example.draf.personsListFull.dragDropListItem.offsetEnd
import kotlinx.coroutines.Job

class StudentsDragListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggedDistance by mutableFloatStateOf(0f)
    var draggedItem by mutableStateOf<LazyListItemInfo?>(null)
    var draggedItemIndex by mutableStateOf<Int?>(null)
    var overscrollJob by mutableStateOf<Job?>(null)
    val currentItem: LazyListItemInfo?
        get() = draggedItemIndex?.let {
            lazyListState.getVisibleListItemInfo(absolute = it)
        }
    val initialOffset: Pair<Int, Int>?
        get() = draggedItem?.let { item ->
            Pair(item.offset, item.offsetEnd)
        }
    val itemDisplacement: Float?
        get() = draggedItemIndex?.let { index ->
            lazyListState.getVisibleListItemInfo(absolute = index)
        }?.let {
            item -> (draggedItem?.offset ?: 0f).toFloat() + draggedDistance - item.offset
        }

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo?.firstOrNull { item ->
            offset.y.toInt() in item.offset..(item.offset + item.size)
        }?.also { item ->
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
                    endOffset < item.offset || startOffset > item.offsetEnd || item.index == hovered.index
                }.firstOrNull { item ->
                    val delta = startOffset - hovered.offsetEnd
                    when {
                        delta > 0 -> (endOffset > item.offsetEnd)
                        else -> (startOffset < item.offset)
                    }
                } ?.also { item ->
                    draggedItemIndex?.let { currentIndex ->
                        onMove(currentIndex, item.index)
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
        overscrollJob?.cancel()
    }

    fun checkForOverscroll() : Float {
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

@Composable
fun RememberDragListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
) : StudentsDragListState {
    return StudentsDragListState(
        lazyListState = lazyListState,
        onMove = onMove
    )
}