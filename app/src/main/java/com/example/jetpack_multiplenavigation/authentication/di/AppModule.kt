package com.example.jetpack_multiplenavigation.authentication.di

import android.content.Context
import com.example.jetpack_multiplenavigation.authentication.data.local.AuthPreferences
import com.example.jetpack_multiplenavigation.authentication.data.remote.AuthApi
import com.example.jetpack_multiplenavigation.authentication.data.repository.AuthRepositoryImpl
import com.example.jetpack_multiplenavigation.authentication.domain.repository.AuthRepository
import com.example.jetpack_multiplenavigation.authentication.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAuthPreferences(@ApplicationContext context: Context) : AuthPreferences {
        return AuthPreferences(context)
    }

    @Provides
    @Singleton
    fun providesAuthApi() : AuthApi {
        return RetrofitClient.authApi
    }

    @Provides
    @Singleton
    fun providesRepository(
        @ApplicationContext context: Context,
        authApi: AuthApi,
        preferences: AuthPreferences
    ) : AuthRepository {
        return AuthRepositoryImpl(context, authApi, preferences)
    }
}