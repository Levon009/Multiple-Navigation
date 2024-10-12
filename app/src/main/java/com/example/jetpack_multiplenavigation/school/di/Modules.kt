package com.example.jetpack_multiplenavigation.school.di

import androidx.room.Room
import com.example.jetpack_multiplenavigation.school.data.database.SchoolDao
import com.example.jetpack_multiplenavigation.school.data.database.SchoolDatabase
import com.example.jetpack_multiplenavigation.school.presentation.SchoolViewModel
import com.example.jetpack_multiplenavigation.school.domain.repository.SchoolRepository
import com.example.jetpack_multiplenavigation.school.data.repository.SchoolRepositoryImpl
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