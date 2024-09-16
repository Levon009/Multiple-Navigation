package com.example.jetpack_multiplenavigation.chat.di

import com.example.jetpack_multiplenavigation.chat.presentation.ChatViewModel
import com.example.jetpack_multiplenavigation.chat.retrofit.ChatRetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val chatFcmModule = module {
    single { ChatRetrofitClient.api }
    viewModelOf(::ChatViewModel)
}