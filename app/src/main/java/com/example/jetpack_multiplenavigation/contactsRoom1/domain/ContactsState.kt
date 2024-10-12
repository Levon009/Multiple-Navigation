package com.example.jetpack_multiplenavigation.contactsRoom1.domain

import com.example.jetpack_multiplenavigation.contactsRoom1.domain.model.Contact

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val contactId: Int = -1,
    val firstName: String = "",
    val secondName: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val isUpdatingContact: Boolean = false,
    val contactsSortType: ContactsSortType = ContactsSortType.FIRST_NAME
)
