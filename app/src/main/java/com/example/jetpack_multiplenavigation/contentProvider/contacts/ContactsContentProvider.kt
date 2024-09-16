package com.example.jetpack_multiplenavigation.contentProvider.contacts

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateListOf
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.ContactDetails
import com.example.jetpack_multiplenavigation.contentProvider.contacts.data.Phone
import com.example.jetpack_multiplenavigation.contentProvider.contacts.presentation.PhoneContactsViewModel

fun contactsContentProvider(
    context: Context,
    phoneContactsViewModel: PhoneContactsViewModel
) {
    val contactsDetailsBaseUri = ContactsContract.Contacts.CONTENT_URI
    val contactsDetailsProjection = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    )
    val phonesBaseUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    val phonesProjection = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    context.contentResolver.query(
        contactsDetailsBaseUri,
        contactsDetailsProjection,
        null,
        null,
        null
    ) ?.use { cursor ->
        val contactsDetailsIdColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
        val contactsDetailsNameColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        val contactsDetails = mutableStateListOf<ContactDetails>()
        while (cursor.moveToNext()) {
            val id = cursor.getLong(contactsDetailsIdColumnIndex)
            val name = cursor.getString(contactsDetailsNameColumnIndex)
            contactsDetails.add(ContactDetails(id, name))
        }
        phoneContactsViewModel.updateContactsDetails(contactsDetails)
    }

    context.contentResolver.query(
        phonesBaseUri,
        phonesProjection,
        null,
        null,
        null
    ) ?.use { cursor ->
        val phoneIdColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
        val phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val phones = mutableStateListOf<Phone>()
        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            val id = cursor.getLong(phoneIdColumnIndex)
            val phoneNumber = cursor.getString(phoneNumberColumnIndex)
            phones.add(Phone(id, phoneNumber))
        }
        phoneContactsViewModel.updatePhones(phones)
    }
}