package com.example.jetpack_multiplenavigation.listCarsFull.swipeToRefreshCarsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SwipeToRefreshCarsViewModel : ViewModel() {
    private var job: CompletableJob? = null

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    fun checkForRefresh() {
        viewModelScope.launch(Dispatchers.IO + job!!) {
            _isRefreshing.update { true }
            delay(2500L)
            _isRefreshing.update { false }
            cancel()
        }.cancel()
    }

    fun initializeJog() {
        job = Job()
        job?.invokeOnCompletion {
            it?.let {
                var message = it.message
                if (message.isNullOrBlank()) {
                    message = "Unknown error. result - $job"
                }
            }
        }
    }
}