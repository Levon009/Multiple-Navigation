package com.example.jetpack_multiplenavigation.uploadFileRetrofit.database

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part

interface FileUploadApi {

    @Multipart
    suspend fun uploadFile(
        @Part file: MultipartBody.Part
    )

    companion object {
        const val UPLOAD_FILE_URL = "http://192.160.0.166/" // Not correct
    }
}