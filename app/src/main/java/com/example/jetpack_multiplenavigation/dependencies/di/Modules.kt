package com.example.jetpack_multiplenavigation.dependencies.di

import com.example.jetpack_multiplenavigation.employee_dependencies.data.employeeFactory.EmployeeFactory
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.repository.EmployeeRepository
import com.example.jetpack_multiplenavigation.employee_dependencies.data.repository.EmployeeRepositoryImpl
import com.example.jetpack_multiplenavigation.employee_dependencies.presentation.EmployeeViewModel
import com.example.jetpack_multiplenavigation.dependencies.data.DbClient
import com.example.jetpack_multiplenavigation.dependencies.koinViewModel.KoinViewModel
import com.example.jetpack_multiplenavigation.dependencies.repository.KoinRepository
import com.example.jetpack_multiplenavigation.dependencies.repository.KoinRepositoryImpl
import com.example.jetpack_multiplenavigation.pickSaveImage.ImageViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val platformModule = module {
    singleOf(::DbClient)
}

val sharedModule = module {
    singleOf(::KoinRepositoryImpl).bind<KoinRepository>()
    viewModelOf(::KoinViewModel)
}

val employeeModule = module {
    singleOf(::EmployeeRepositoryImpl).bind<EmployeeRepository>()
    viewModelOf(::EmployeeViewModel)
    factoryOf(::EmployeeFactory)
}

val imageViewModule = module {
    viewModelOf(::ImageViewModel)
}