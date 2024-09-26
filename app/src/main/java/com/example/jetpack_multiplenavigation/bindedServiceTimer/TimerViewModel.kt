package com.example.jetpack_multiplenavigation.bindedServiceTimer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var counter by mutableIntStateOf(0)
        private set
    var isPaused by mutableStateOf(false)
        private set

    fun updateCounter(i: Int) {
        counter = i
    }

    fun updatePaused() {
        isPaused = !isPaused
    }
}