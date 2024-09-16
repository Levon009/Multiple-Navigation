package com.example.jetpack_multiplenavigation.students.data

import com.example.draf.students.data.model.Student

sealed interface StudentsEvent {
    object SaveStudent : StudentsEvent
    data class UpdateStudent(val student: Student) : StudentsEvent
    data class DeleteStudent(val student: Student) : StudentsEvent
    object DeleteAllStudents : StudentsEvent
    data class SetStudentId(val id: Int) : StudentsEvent
    data class SetFirstName(val firstName: String) : StudentsEvent
    data class SetSecondName(val secondName: String) : StudentsEvent
    data class SetAge(val age: Int) : StudentsEvent
    data class SortStudents(val sortType: StudentsSortType) : StudentsEvent
    data class ShowLanguagesDialog(val studentID: Int) : StudentsEvent
    object HideLanguagesDialog : StudentsEvent
    object ShowMarksDialog : StudentsEvent
    object HideMarksDialog : StudentsEvent
}