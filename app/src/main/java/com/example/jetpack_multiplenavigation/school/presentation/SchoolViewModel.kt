package com.example.jetpack_multiplenavigation.school.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.school.domain.modul.StudentsOfSubject
import com.example.jetpack_multiplenavigation.school.domain.modul.SubjectsOfStudent
import com.example.jetpack_multiplenavigation.school.domain.repository.SchoolRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val repository: SchoolRepository
) : ViewModel() {

    private val _studentsOfSubject = MutableStateFlow<List<StudentsOfSubject>>(emptyList())
    val studentsOfSubject = _studentsOfSubject.asStateFlow()

    private val _subjectsOfStudent = MutableStateFlow<List<SubjectsOfStudent>>(emptyList())
    val subjectsOfStudent = _subjectsOfStudent.asStateFlow()

    init {
        repository.setAllLists()
    }

    fun getStudentsOfSubject(subjectName: String) {
        viewModelScope.launch {
            delay(1500)
            repository.getStudentsOfSubject(subjectName).collectLatest { students ->
                _studentsOfSubject.update { students }
            }
        }
    }

    fun getSubjectsOfStudent(studentName: String) {
        viewModelScope.launch {
            delay(1500)
            repository.getSubjectsOfStudent(studentName).collectLatest { subjects ->
                _subjectsOfStudent.update { subjects }
            }
        }
    }
}