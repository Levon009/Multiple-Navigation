package com.example.jetpack_multiplenavigation.school.domain.modul

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "students3_table")
data class Student3(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("student3_name")
    val studentName: String,
    @ColumnInfo("semester")
    val semester: Int,
    val marks: List<Mark> = emptyList()
)
