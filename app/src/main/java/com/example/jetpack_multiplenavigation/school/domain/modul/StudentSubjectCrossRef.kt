package com.example.jetpack_multiplenavigation.school.domain.modul

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["student3_name", "subject3_name"])
data class StudentSubjectCrossRef(
    @ColumnInfo(name = "student3_name")
    val studentName: String,
    @ColumnInfo(name = "subject3_name")
    val subjectName: String
)
