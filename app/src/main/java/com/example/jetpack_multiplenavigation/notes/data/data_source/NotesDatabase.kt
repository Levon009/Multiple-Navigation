package com.example.jetpack_multiplenavigation.notes.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack_multiplenavigation.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context) : NotesDatabase {
            val templateInstance = INSTANCE
            if (templateInstance != null) {
                return templateInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    NoteDao.NOTES_DATABASE
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}