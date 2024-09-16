package com.example.jetpack_multiplenavigation.students.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class StudentMark(
    val mark: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
