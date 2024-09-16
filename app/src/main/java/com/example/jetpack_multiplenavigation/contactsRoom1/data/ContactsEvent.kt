package com.example.jetpack_multiplenavigation.contactsRoom1.data

import com.example.jetpack_multiplenavigation.contactsRoom1.data.model.Contact

sealed interface ContactsEvent {
    object SaveContact : ContactsEvent
    data class SetFirstName(val firstName: String) : ContactsEvent
    data class SetSecondName(val secondName: String) : ContactsEvent
    data class SetPhoneNumber(val phoneNumber: String) : ContactsEvent
    data class SetContactID(val id: Int) : ContactsEvent
    data class SortContacts(val contactsSortType: ContactsSortType) : ContactsEvent
    data class UpdateContact(val contact: Contact) : ContactsEvent
    data class DeleteContact(val contact: Contact) : ContactsEvent
    object DeleteAllContacts : ContactsEvent
    object ShowDialog : ContactsEvent
    object HideDialog : ContactsEvent
    object ShowUpdateDialog : ContactsEvent
    object HideUpdateDialog : ContactsEvent
}