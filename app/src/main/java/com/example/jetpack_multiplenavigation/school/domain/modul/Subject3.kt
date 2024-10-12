package com.example.jetpack_multiplenavigation.school.domain.modul

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "subjects3_table")
data class Subject3(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("subject3_name")
    val subjectName: String
)
