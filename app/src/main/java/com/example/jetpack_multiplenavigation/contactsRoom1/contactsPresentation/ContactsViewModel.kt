package com.example.jetpack_multiplenavigation.contactsRoom1.contactsPresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.contactsRoom1.contactsDatabase.ContactsDao
import com.example.jetpack_multiplenavigation.contactsRoom1.data.ContactsEvent
import com.example.jetpack_multiplenavigation.contactsRoom1.data.ContactsSortType
import com.example.jetpack_multiplenavigation.contactsRoom1.data.ContactsState
import com.example.jetpack_multiplenavigation.contactsRoom1.data.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val contactsDao: ContactsDao
) : ViewModel() {
    private val _contactsSortType = MutableStateFlow(ContactsSortType.FIRST_NAME)
    private val _contacts = _contactsSortType.flatMapLatest { contactsSortType ->
        when(contactsSortType) {
            ContactsSortType.FIRST_NAME -> contactsDao.getContactsOrderByFirstName()
            ContactsSortType.SECOND_NAME -> contactsDao.getContactsOrderBySecondName()
            ContactsSortType.PHONE_NUMBER -> contactsDao.getContactsOrderByPhone()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(ContactsState())
    val state = combine(_state, _contactsSortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            contactsSortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactsState())

    fun onEvent(event: ContactsEvent) {
        when(event) {
            ContactsEvent.SaveContact -> {
                val firstName = state.value.firstName
                val secondName = state.value.secondName
                val phoneNumber = state.value.phoneNumber

                if (firstName.isBlank() || secondName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                val contact = Contact(firstName, secondName, phoneNumber)
                viewModelScope.launch {
                    contactsDao.upsertContact(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        isUpdatingContact = false,
                        contactId = -1,
                        firstName = "",
                        secondName = "",
                        phoneNumber = ""
                    )
                }
            }
            is ContactsEvent.UpdateContact -> {
                val id = state.value.contactId
                val firstName = state.value.firstName
                val secondName = state.value.secondName
                val phoneNumber = state.value.phoneNumber

                if (firstName.isBlank() || secondName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                val contact = Contact(firstName, secondName, phoneNumber, id)
                viewModelScope.launch {
                    contactsDao.updateContact(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        isUpdatingContact = false,
                        contactId = -1,
                        firstName = "",
                        secondName = "",
                        phoneNumber = ""
                    )
                }
            }
            is ContactsEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactsDao.deleteContact(event.contact)
                }
            }
            ContactsEvent.DeleteAllContacts -> {
                viewModelScope.launch {
                    contactsDao.deleteAllContacts()
                }
            }
            is ContactsEvent.SortContacts -> {
                _contactsSortType.value = event.contactsSortType
            }
            is ContactsEvent.SetFirstName -> {
                _state.update { it.copy(firstName = event.firstName) }
            }
            is ContactsEvent.SetSecondName -> {
                _state.update { it.copy(secondName = event.secondName) }
            }
            is ContactsEvent.SetPhoneNumber -> {
                _state.update { it.copy(phoneNumber = event.phoneNumber) }
            }
            ContactsEvent.ShowDialog -> {
                _state.update { it.copy(isAddingContact = true) }
            }
            ContactsEvent.HideDialog -> {
                _state.update { it.copy(isAddingContact = false) }
            }
            is ContactsEvent.ShowUpdateDialog -> {
                _state.update { it.copy(isUpdatingContact = true) }
            }
            is ContactsEvent.HideUpdateDialog -> {
                _state.update { it.copy(isUpdatingContact = false) }
            }
            is ContactsEvent.SetContactID -> {
                _state.update { it.copy(contactId = event.id) }
            }
        }
    }
}