package com.example.jetpack_multiplenavigation.students.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "programming_language")
data class ProgrammingLanguage(
    val name: String,
    val studentId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
