package com.example.jetpack_multiplenavigation.listCarsFull.screens

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_multiplenavigation.listCarsFull.carsDragDropListItem.rememberCarsLazyListState
import com.example.jetpack_multiplenavigation.listCarsFull.data.model.Car
import com.example.jetpack_multiplenavigation.listCarsFull.swipeToDeleteCarItem.SwipeToRefreshCarContainer
import com.example.jetpack_multiplenavigation.listCarsFull.swipeToRefreshCarsViewModel.SwipeToRefreshCarsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun CarsList(
    supportingText: String,
    cars: MutableList<Car>,
    onMove: (Int, Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val dragDropCarsListState = rememberCarsLazyListState(onMove = onMove)
    val swipeToRefreshCarsViewModel = viewModel<SwipeToRefreshCarsViewModel>()
    val isRefreshing = swipeToRefreshCarsViewModel.isRefreshing.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    swipeToRefreshCarsViewModel.initializeJog()
    var overScrollJob by remember {
        mutableStateOf<Job?>(null)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = swipeToRefreshCarsViewModel::checkForRefresh,
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
                                dragDropCarsListState.onDragStart(offset)
                            },
                            onDrag = { change, offset ->
                                change.consume()
                                dragDropCarsListState.onDrag(offset)

                                if (overScrollJob?.isActive  == true)
                                    return@detectDragGesturesAfterLongPress

                                dragDropCarsListState
                                    .checkForOverScroll()
                                    .takeIf { it != 0f }
                                    ?.let {
                                        overScrollJob = scope.launch {
                                            dragDropCarsListState.lazyListState.scrollBy(it)
                                        }
                                    } ?: kotlin.run { overScrollJob?.cancel() }
                            },
                            onDragEnd = dragDropCarsListState::onDragInterrupt,
                            onDragCancel = dragDropCarsListState::onDragInterrupt
                        )
                    }
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                state = dragDropCarsListState.lazyListState
            ) {
                itemsIndexed(
                    items = cars,
                    key = { _, car ->
                        car.hashCode()
                    }
                ) { index, car ->
                    SwipeToRefreshCarContainer(
                        car = car,
                        onRemove = {
                            cars -= car
                        }
                    ) {
                        CarListItemContent(
                            name = car.name,
                            supportingText = supportingText,
                            index = index,
                            dragDropCarsListState = dragDropCarsListState
                        )
                    }
                }
            }
        }
    }
}