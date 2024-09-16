package com.example.jetpack_multiplenavigation.snackBarSB.di

import com.example.jetpack_multiplenavigation.snackBarSB.presentation.SnackBarViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val snackBarModule = module {
    viewModelOf(::SnackBarViewModel)
}