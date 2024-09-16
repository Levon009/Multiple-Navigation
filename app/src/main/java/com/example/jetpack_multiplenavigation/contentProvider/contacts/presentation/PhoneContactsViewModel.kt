package com.example.jetpack_multiplenavigation.contentProvider.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.Contact
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.ContactDetails
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.Phone

class PhoneContactsViewModel : ViewModel() {

    var contactsDetails by mutableStateOf(emptyList<ContactDetails>())
        private set

    var phones by mutableStateOf(emptyList<Phone>())
        private set

    var contacts by mutableStateOf(emptyList<Contact>())
        private set

    fun updateContactsDetails(contactsDetails: List<ContactDetails>) {
        this.contactsDetails = contactsDetails
    }

    fun updatePhones(contactsPhones: List<Phone>) {
        phones = contactsPhones
    }

    fun updateContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }
}