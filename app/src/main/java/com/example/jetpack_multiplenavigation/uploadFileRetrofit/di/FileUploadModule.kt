package com.example.jetpack_multiplenavigation.uploadFileRetrofit.di

import com.example.jetpack_multiplenavigation.uploadFileRetrofit.database.RetrofitClient3
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.presentation.FileUploadViewModel
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.repository.FileUploadRepository
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.repository.FileUploadRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fileUploadRepository = module {
    single { RetrofitClient3.api }
    factoryOf(::FileUploadRepositoryImpl).bind<FileUploadRepository>()
    viewModelOf(::FileUploadViewModel)
}