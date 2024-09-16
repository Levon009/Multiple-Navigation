package com.example.jetpack_multiplenavigation.school.di

import androidx.room.Room
import com.example.jetpack_multiplenavigation.school.database.SchoolDao
import com.example.jetpack_multiplenavigation.school.database.SchoolDatabase
import com.example.jetpack_multiplenavigation.school.presentation.SchoolViewModel
import com.example.jetpack_multiplenavigation.school.repository.SchoolRepository
import com.example.jetpack_multiplenavigation.school.repository.SchoolRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val schoolDBModule = module {
    single { Room.databaseBuilder(
        get(),
        SchoolDatabase::class.java,
        SchoolDao.SCHOOL_DB
    ).build() }
    single { get<SchoolDatabase>().dao }
    factoryOf(::SchoolRepositoryImpl).bind<SchoolRepository>()
    viewModelOf(::SchoolViewModel)
}