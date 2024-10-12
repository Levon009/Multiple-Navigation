package com.example.jetpack_multiplenavigation.students.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "students_table")
data class Student(
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("second_name")
    val secondName: String,
    @ColumnInfo("student_age")
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("student_marks")
    val marks: List<StudentMark> = emptyList(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (secondName != other.secondName) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + firstName.hashCode()
        result = 31 * result + secondName.hashCode()
        result = 31 * result + age
        return result
    }
}
