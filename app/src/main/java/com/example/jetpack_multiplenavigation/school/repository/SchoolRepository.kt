package com.example.jetpack_multiplenavigation.school.repository

import com.example.jetpack_multiplenavigation.school.data.modul.StudentsOfSubject
import com.example.jetpack_multiplenavigation.school.data.modul.SubjectsOfStudent
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun getSubjectsOfStudent(studentName: String) : Flow<List<SubjectsOfStudent>>
    suspend fun getStudentsOfSubject(subjectName: String) : Flow<List<StudentsOfSubject>>
    fun setAllLists()
}