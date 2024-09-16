package com.example.draf.swipeToRefresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RefreshViewModel : ViewModel() {
    private var job: CompletableJob? = null
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    fun refreshStuff() {
        viewModelScope.launch(Dispatchers.Main + job!!) {
            _isRefreshing.update { true }
            delay(2000L)
            _isRefreshing.update { false }
            delay(1000L)
       }.cancel()
    }

    fun initializeJob() {
        job = Job()
        job?.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if (msg.isNullOrBlank()) {
                    msg = "Unknown error. result - ${job}"
                }
            }
        }
    }
}