package com.example.jetpack_multiplenavigation.daggerCustom.authentication.di

import android.content.Context
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.remote.AuthApi
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.repository.AuthRepositoryImpl
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.domain.repository.AuthRepository
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.retrofit.RetrofitClient

class AppModuleImpl(
    private val appContext: Context
) : AppModule {

    override val authApi: AuthApi by lazy {
        RetrofitClient.authApi
    }
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authApi)
    }
}