package com.example.jetpack_multiplenavigation.dependencies.koinViewModel

import androidx.lifecycle.ViewModel
import com.example.jetpack_multiplenavigation.dependencies.repository.KoinRepository

class KoinViewModel(
    private val koinRepository: KoinRepository
) : ViewModel() {

    fun getHelloWorldString() : String {
        return koinRepository.helloWorld()
    }
}