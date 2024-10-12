package com.example.jetpack_multiplenavigation.contactsRoom1.di

import androidx.room.Room
import com.example.jetpack_multiplenavigation.contactsRoom1.data.contactsDatabase.ContactsDao
import com.example.jetpack_multiplenavigation.contactsRoom1.data.contactsDatabase.ContactsDatabase
import com.example.jetpack_multiplenavigation.contactsRoom1.presentation.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val contactsDatabaseModule = module {
    single { Room.databaseBuilder(
        get(),
        ContactsDatabase::class.java,
        ContactsDao.CONTACTS_DB
    ).build() }
    single { get<ContactsDatabase>().dao }
    viewModelOf(::ContactsViewModel)
}