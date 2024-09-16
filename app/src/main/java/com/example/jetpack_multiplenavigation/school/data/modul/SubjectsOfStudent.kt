package com.example.jetpack_multiplenavigation.school.data.modul

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class SubjectsOfStudent(
    @Embedded val student3: Student3,
    @Relation(
        parentColumn = "student3_name",
        entityColumn = "subject3_name",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjectsList: List<Subject3>
)
