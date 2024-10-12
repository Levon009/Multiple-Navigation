package com.example.jetpack_multiplenavigation.students.data.studentsDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.jetpack_multiplenavigation.students.domain.model.Student
import com.example.jetpack_multiplenavigation.students.domain.model.ProgrammingLanguage
import com.example.jetpack_multiplenavigation.students.domain.model.StudentMark
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDao {
    // Students
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Upsert
    suspend fun upsertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE from students_table")
    suspend fun deleteAllStudents()

    @Query("SELECT * FROM students_table ORDER BY first_name ASC")
    fun getStudentsOrderByFirstName() : Flow<List<Student>>

    @Query("SELECT * FROM students_table ORDER BY second_name ASC")
    fun getStudentsOrderBySecondName() : Flow<List<Student>>
    @Query("SELECT * FROM students_table ORDER BY student_age ASC")
    fun getStudentsOrderByAge() : Flow<List<Student>>

    // Programming Language
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLanguage(language: ProgrammingLanguage)

    @Upsert
    suspend fun upsertLanguage(language: ProgrammingLanguage)

    @Update
    suspend fun updateLanguage(language: ProgrammingLanguage)

    @Delete
    suspend fun deleteLanguage(language: ProgrammingLanguage)

    @Query("DELETE FROM programming_language")
    suspend fun deleteAllLanguage()

    @Query("SELECT * FROM programming_language ORDER BY id ASC")
    fun getProgrammingLanguage() : Flow<List<ProgrammingLanguage>>

    @Query("SELECT * FROM programming_language WHERE studentId = :studentID")
    fun getStudentLanguage(studentID: Int) : Flow<List<ProgrammingLanguage>>

    // Marks
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMark(mark: StudentMark)

    @Upsert
    suspend fun upsertMark(mark: StudentMark)

    @Update
    suspend fun updateMark(mark: StudentMark)

    @Delete
    suspend fun deleteMark(mark: StudentMark)

    @Query("DELETE FROM studentmark")
    suspend fun deleteAllMarks()

    @Query("SELECT * FROM studentmark ORDER BY id ASC")
    fun getAllMarks() : Flow<List<StudentMark>>

    companion object {
        const val STUDENTS_DB = "Students.db"
    }
}