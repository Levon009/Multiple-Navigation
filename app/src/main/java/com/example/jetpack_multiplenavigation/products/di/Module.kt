package com.example.jetpack_multiplenavigation.products.di

import com.example.jetpack_multiplenavigation.products.data.retrofit.RetrofitClient
import com.example.jetpack_multiplenavigation.products.domain.repository.ProductsRepository
import com.example.jetpack_multiplenavigation.products.data.repository.ProductsRepositoryImpl
import com.example.jetpack_multiplenavigation.products.presentation.ProductsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productsModule = module {
    factoryOf(::ProductsRepositoryImpl).bind<ProductsRepository>()
    viewModelOf(::ProductsViewModel)
}

val networkModule = module {
    single { RetrofitClient.productsApi }
}