package com.example.jetpack_multiplenavigation.students.data

import com.example.jetpack_multiplenavigation.students.domain.model.Student

data class StudentsState(
    val students: List<Student> = emptyList(),
    val studentId: Int = -1,
    val firstName: String = "",
    val secondName: String = "",
    val age: Int = 0,
    val studentsSortType: StudentsSortType = StudentsSortType.FIRST_NAME,
    val showLanguagesDialog: Boolean = false,
    val showMarksDialog: Boolean = false
)
