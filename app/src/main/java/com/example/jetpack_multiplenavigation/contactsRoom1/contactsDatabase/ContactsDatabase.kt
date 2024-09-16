package com.example.jetpack_multiplenavigation.contactsRoom1.contactsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpack_multiplenavigation.contactsRoom1.data.model.Contact
import com.example.jetpack_multiplenavigation.contactsRoom1.data.model.DogListConverters

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DogListConverters::class)
abstract class ContactsDatabase : RoomDatabase() {
    abstract val dao: ContactsDao

    companion object {
        private var INSTANCE: ContactsDatabase? = null

        fun getDatabase(context: Context) : ContactsDatabase {
            val templateInstance = INSTANCE
            if (templateInstance != null) {
                return templateInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ContactsDatabase::class.java,
                    ContactsDao.CONTACTS_DB
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}