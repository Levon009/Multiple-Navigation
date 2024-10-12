package com.example.jetpack_multiplenavigation.students.data.studentsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpack_multiplenavigation.students.domain.model.Student
import com.example.jetpack_multiplenavigation.students.domain.model.MarksConverter
import com.example.jetpack_multiplenavigation.students.domain.model.ProgrammingLanguage
import com.example.jetpack_multiplenavigation.students.domain.model.StudentMark

@Database(
    entities = [Student::class,
        ProgrammingLanguage::class,
        StudentMark::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MarksConverter::class)
abstract class StudentsDatabase : RoomDatabase() {
    abstract val dao: StudentsDao

    companion object {
        @Volatile
        private var INSTANCE: StudentsDatabase? = null

        fun getDatabase(context: Context) : StudentsDatabase {
            val templateInstance = INSTANCE
            if (templateInstance != null) {
                return templateInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentsDatabase::class.java,
                    StudentsDao.STUDENTS_DB
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}