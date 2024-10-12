package com.example.jetpack_multiplenavigation.school.domain.repository

import com.example.jetpack_multiplenavigation.school.domain.modul.StudentsOfSubject
import com.example.jetpack_multiplenavigation.school.domain.modul.SubjectsOfStudent
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun getSubjectsOfStudent(studentName: String) : Flow<List<SubjectsOfStudent>>
    suspend fun getStudentsOfSubject(subjectName: String) : Flow<List<StudentsOfSubject>>
    fun setAllLists()
}