package com.example.jetpack_multiplenavigation.school.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpack_multiplenavigation.school.domain.modul.MarksConverter
import com.example.jetpack_multiplenavigation.school.domain.modul.Student3
import com.example.jetpack_multiplenavigation.school.domain.modul.StudentSubjectCrossRef
import com.example.jetpack_multiplenavigation.school.domain.modul.Subject3

@Database(
    entities = [Student3::class, Subject3::class, StudentSubjectCrossRef::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MarksConverter::class)
abstract class SchoolDatabase : RoomDatabase() {
    abstract val dao: SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getDatabase(context: Context) : SchoolDatabase {
            val templateInstance = INSTANCE
            if (templateInstance != null) {
                return templateInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    SchoolDao.SCHOOL_DB
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}