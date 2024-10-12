package com.example.jetpack_multiplenavigation.students.di

import androidx.room.Room
import com.example.jetpack_multiplenavigation.students.data.studentsDatabase.StudentsDao
import com.example.jetpack_multiplenavigation.students.data.studentsDatabase.StudentsDatabase
import com.example.jetpack_multiplenavigation.students.presentation.StudentsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val studentsDatabaseModule = module {
    single { Room.databaseBuilder(
        get(),
        StudentsDatabase::class.java,
        StudentsDao.STUDENTS_DB
    ).build() }

    single { get<StudentsDatabase>().dao }

    viewModelOf(::StudentsViewModel)
}