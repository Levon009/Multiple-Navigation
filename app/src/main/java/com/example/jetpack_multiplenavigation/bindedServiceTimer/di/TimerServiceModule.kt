package com.example.jetpack_multiplenavigation.bindedServiceTimer.di

import com.example.jetpack_multiplenavigation.bindedServiceTimer.TimerViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val timerServiceModule = module {
    viewModelOf(::TimerViewModel)
}