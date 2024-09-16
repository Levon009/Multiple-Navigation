package com.example.jetpack_multiplenavigation.students.studentsDragDropListItem

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState

val LazyListItemInfo.offsetEnd: Int
    get() = this.offset + size

fun LazyListState.getVisibleListItemInfo(absolute: Int) : LazyListItemInfo? {
    return this.layoutInfo.visibleItemsInfo.getOrNull(absolute - this.layoutInfo.visibleItemsInfo.first().index)
}

fun <T> MutableList<T>.move(from: Int, to: Int) {
    if (from == to)
        return

    val item = this.removeAt(from) ?: return
    this.add(to, item)
}