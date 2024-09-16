package com.example.jetpack_multiplenavigation.uploadFileRetrofit.repository

import java.io.File

interface FileUploadRepository {
    suspend fun uploadFile(file: File) : Boolean
}