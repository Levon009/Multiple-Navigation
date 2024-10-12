package com.example.jetpack_multiplenavigation.products.di

import com.example.jetpack_multiplenavigation.products.data.retrofit.RetrofitClient
import com.example.jetpack_multiplenavigation.products.domain.repository.ProductsRepository
import com.example.jetpack_multiplenavigation.products.data.repository.ProductsRepositoryImpl
import com.example.jetpack_multiplenavigation.products.domain.use_cases.CreatePost
import com.example.jetpack_multiplenavigation.products.domain.use_cases.DeletePost
import com.example.jetpack_multiplenavigation.products.domain.use_cases.GetPosts
import com.example.jetpack_multiplenavigation.products.domain.use_cases.GetProducts
import com.example.jetpack_multiplenavigation.products.domain.use_cases.ProductsUseCases
import com.example.jetpack_multiplenavigation.products.domain.use_cases.UpdatePost
import com.example.jetpack_multiplenavigation.products.presentation.ProductsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productsModule = module {
    factoryOf(::ProductsRepositoryImpl).bind<ProductsRepository>()
    viewModelOf(::ProductsViewModel)
    singleOf(::GetProducts)
    singleOf(::GetPosts)
    singleOf(::CreatePost)
    singleOf(::UpdatePost)
    singleOf(::DeletePost)
    single { ProductsUseCases(get(), get(), get(), get(), get()) }
}

val networkModule = module {
    single { RetrofitClient.productsApi }
}