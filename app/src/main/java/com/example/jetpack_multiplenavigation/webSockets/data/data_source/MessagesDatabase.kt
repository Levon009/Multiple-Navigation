package com.example.jetpack_multiplenavigation.webSockets.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto

@Database(entities = [MessageDto::class], version = 1, exportSchema = true)
abstract class MessagesDatabase : RoomDatabase() {

    abstract val dao: MessagesDao

    companion object {
        @Volatile
        private var INSTANCE: MessagesDatabase? = null

        fun getInstance(context: Context) : MessagesDatabase {

            val templateInstance = INSTANCE
            if (templateInstance != null) {
                return templateInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessagesDatabase::class.java,
                    MessagesDao.MESSAGES_DB
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}