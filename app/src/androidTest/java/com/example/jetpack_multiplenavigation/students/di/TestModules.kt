package com.example.jetpack_multiplenavigation.students.di

import com.example.jetpack_multiplenavigation.students.studentsDatabase.StudentsDatabase
import com.example.jetpack_multiplenavigation.students.studentsViewModel.StudentsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val instrumentedTestModule = module {
    single { StudentsDatabase.getDatabase(get()) }
    single { get<StudentsDatabase>().dao }
    viewModelOf(::StudentsViewModel)
}