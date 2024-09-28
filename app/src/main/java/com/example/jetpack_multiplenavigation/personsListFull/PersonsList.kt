package com.example.jetpack_multiplenavigation.personsListFull

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_multiplenavigation.personsListFull.data.Person
import com.example.draf.personsListFull.dragDropListItem.rememberDragListState
import com.example.jetpack_multiplenavigation.personsListFull.dropOptionMenu.getAllDropMenuItems
import com.example.jetpack_multiplenavigation.personsListFull.swipeToDelete.SwipeToDeleteContainer
import com.example.jetpack_multiplenavigation.personsListFull.swipeToRefresh.RefreshViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun PersonsList(
    supportingText: String,
    persons: MutableList<Person>,
    onMove: (Int, Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val dragDropListState = rememberDragListState(onMove = onMove)
    var overScrollJob by remember {
        mutableStateOf<Job?>(null)
    }
    val dropMenuItems = getAllDropMenuItems()
    val refreshViewModel = viewModel<RefreshViewModel>()
    val isRefreshing = refreshViewModel.isRefreshing.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing.value)
    refreshViewModel.initializeJob()

    Surface(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = refreshViewModel::refreshStuff,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Red
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(true) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = { offset ->
                                dragDropListState.onDragStart(offset)
                            },
                            onDrag = { change, offset ->
                                change.consume()
                                dragDropListState.onDrag(offset)

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
                                dragDropListState.onDragInterrupt()
                            },
                            onDragEnd = {
                                dragDropListState.onDragInterrupt()
                            },
                        )
                    }
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                state = dragDropListState.lazyListState
            ) {
                itemsIndexed(
                    items = persons,
                    key = { _, person ->
                        person.hashCode()
                    }
                ) { index, person ->
                    SwipeToDeleteContainer(
                        person = person,
                        onRemove = {
                            persons -= person
                        }
                    ) {
                        ListItemContent(
                            name = person.name,
                            supportingText = supportingText,
                            index = index,
                            dropMenuItems = dropMenuItems,
                            dragDropListState = dragDropListState
                        )
                    }
                }
            }
        }
    }
}