package com.example.jetpack_multiplenavigation.school.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.jetpack_multiplenavigation.school.domain.modul.Student3
import com.example.jetpack_multiplenavigation.school.domain.modul.StudentSubjectCrossRef
import com.example.jetpack_multiplenavigation.school.domain.modul.StudentsOfSubject
import com.example.jetpack_multiplenavigation.school.domain.modul.Subject3
import com.example.jetpack_multiplenavigation.school.domain.modul.SubjectsOfStudent

@Dao
interface SchoolDao {
    @Upsert
    suspend fun upsertStudent(student3: Student3)

    @Upsert
    suspend fun upsertSubject(subject3: Subject3)

    @Upsert
    suspend fun upsertStudentSubjectCrossRef(studentSubjectCrossRef: StudentSubjectCrossRef)

    @Query("SELECT * FROM students3_table WHERE student3_name = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String) : List<SubjectsOfStudent>

    @Query("SELECT * FROM subjects3_table WHERE subject3_name = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String) : List<StudentsOfSubject>

    companion object {
        const val SCHOOL_DB = "school.db"
    }
}