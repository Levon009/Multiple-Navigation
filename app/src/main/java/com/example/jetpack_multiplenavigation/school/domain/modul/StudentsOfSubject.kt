package com.example.jetpack_multiplenavigation.school.domain.modul

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class StudentsOfSubject(
    @Embedded val subject: Subject3,

    @Relation(
        parentColumn = "subject3_name",
        entityColumn = "student3_name",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val studentsList: List<Student3>
)
