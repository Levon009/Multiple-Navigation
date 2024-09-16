package com.example.jetpack_multiplenavigation.uploadFileRetrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.repository.FileUploadRepository
import kotlinx.coroutines.launch
import java.io.File

class FileUploadViewModel(
    private val repository: FileUploadRepository
) : ViewModel() {

    fun uploadFile(file: File) {
        viewModelScope.launch {
            repository.uploadFile(file)
        }
    }
}