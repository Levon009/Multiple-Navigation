package com.example.jetpack_multiplenavigation.school.data.repository

import com.example.jetpack_multiplenavigation.school.domain.modul.StudentsOfSubject
import com.example.jetpack_multiplenavigation.school.domain.modul.SubjectsOfStudent
import com.example.jetpack_multiplenavigation.school.data.studentSubject3Rel
import com.example.jetpack_multiplenavigation.school.data.students3
import com.example.jetpack_multiplenavigation.school.data.subjects3
import com.example.jetpack_multiplenavigation.school.data.database.SchoolDao
import com.example.jetpack_multiplenavigation.school.domain.repository.SchoolRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SchoolRepositoryImpl(
    private val dao: SchoolDao
) : SchoolRepository {

    override suspend fun getSubjectsOfStudent(studentName: String): Flow<List<SubjectsOfStudent>> = flow {
        val subjectsOfStudent = try {
            dao.getSubjectsOfStudent(studentName)
        } catch (e: Exception) {
            e.printStackTrace()
            return@flow
        }
        emit(subjectsOfStudent)
    }.flowOn(Dispatchers.IO)

    override suspend fun getStudentsOfSubject(subjectName: String): Flow<List<StudentsOfSubject>>  = flow {
        val studentsOfSubject = try {
            dao.getStudentsOfSubject(subjectName)
        } catch (e: Exception) {
            e.printStackTrace()
            return@flow
        }
        emit(studentsOfSubject)
    }.flowOn(Dispatchers.IO)

    override fun setAllLists() {
        CoroutineScope(Dispatchers.IO).launch {
            students3.forEach { dao.upsertStudent(it) }
            subjects3.forEach { dao.upsertSubject(it) }
            studentSubject3Rel.forEach { dao.upsertStudentSubjectCrossRef(it) }
        }
    }
}