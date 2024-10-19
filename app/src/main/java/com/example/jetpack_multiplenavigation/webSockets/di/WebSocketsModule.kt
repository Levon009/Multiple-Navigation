package com.example.jetpack_multiplenavigation.webSockets.di

import com.example.jetpack_multiplenavigation.webSockets.presentation.WebSocketsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val webSocketsModule = module {
    viewModelOf(::WebSocketsViewModel)
}