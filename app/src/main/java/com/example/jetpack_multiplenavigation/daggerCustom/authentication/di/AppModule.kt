package com.example.jetpack_multiplenavigation.daggerCustom.authentication.di

import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.remote.AuthApi
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.domain.repository.AuthRepository

interface AppModule {
    val authApi: AuthApi
    val authRepository: AuthRepository
}