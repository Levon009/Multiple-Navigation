package com.example.jetpack_multiplenavigation.contactsRoom1.contactsDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.jetpack_multiplenavigation.contactsRoom1.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    @Query("SELECT * FROM contacts_table ORDER BY first_name ASC")
    fun getContactsOrderByFirstName() : Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY second_name ASC")
    fun getContactsOrderBySecondName() : Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY phone_number ASC")
    fun getContactsOrderByPhone() : Flow<List<Contact>>

    companion object {
        const val CONTACTS_DB = "Contacts.db"
    }
}