package com.example.jetpack_multiplenavigation.webSockets.di

import com.example.jetpack_multiplenavigation.webSockets.data.data_source.MessagesDatabase
import com.example.jetpack_multiplenavigation.webSockets.data.repository.MessagesRepositoryImpl
import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.AddMessage
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.DeleteAllMessages
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.DeleteMessage
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.GetMessages
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.MessagesUseCases
import com.example.jetpack_multiplenavigation.webSockets.presentation.WebSocketsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val webSocketsModules = module {
    single { MessagesDatabase.getInstance(get()) }
    single { get<MessagesDatabase>().dao }
    singleOf(::MessagesRepositoryImpl).bind<MessagesRepository>()
    singleOf(::AddMessage)
    singleOf(::DeleteMessage)
    singleOf(::GetMessages)
    singleOf(::DeleteAllMessages)
    singleOf(::MessagesUseCases)
    viewModelOf(::WebSocketsViewModel)
}