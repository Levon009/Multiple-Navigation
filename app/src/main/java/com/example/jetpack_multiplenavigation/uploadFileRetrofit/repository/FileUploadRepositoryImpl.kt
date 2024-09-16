package com.example.jetpack_multiplenavigation.uploadFileRetrofit.repository

import com.example.jetpack_multiplenavigation.uploadFileRetrofit.database.FileUploadApi
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class FileUploadRepositoryImpl(
    private val fileUploadApi: FileUploadApi
) : FileUploadRepository {
    override suspend fun uploadFile(file: File): Boolean {
        return try {
            fileUploadApi.uploadFile(
                file = MultipartBody.Part.createFormData(
                    name = "LionImg.jpg",
                    filename = file.name,
                    body = file.asRequestBody()
                )
            )
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: HttpException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}