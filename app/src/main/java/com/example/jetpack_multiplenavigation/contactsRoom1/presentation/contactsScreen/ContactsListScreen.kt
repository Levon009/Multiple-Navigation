package com.example.jetpack_multiplenavigation.contactsRoom1.presentation.contactsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsEvent
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsSortType
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsState
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.model.Contact

@Composable
fun ContactsListScreen(
    navController: NavHostController,
    state: ContactsState,
    onEvent: (ContactsEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(16.dp),
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                FloatingActionButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.Magenta
                    )
                }
                FloatingActionButton(onClick = {
                    onEvent(ContactsEvent.DeleteAllContacts)
                }) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = "Delete all"
                    )
                }
                FloatingActionButton(onClick = {
                    onEvent(ContactsEvent.ShowDialog)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add contact"
                    )
                }
            }
        }
    ) { paddingValues ->
        if (state.isAddingContact) {
            AddContactDialog(
                state = state,
                onEvent = onEvent
            )
        }
        if (state.isUpdatingContact) {
            UpdateContactDialog(
                state = state,
                onEvent = onEvent
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ContactsTopContent(
                    state = state,
                    onEvent = onEvent
                )
                HorizontalDivider()
            }
            items(state.contacts) { contact ->
                ContactsListContent(
                    contact = contact,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
private fun ContactsTopContent(
    state: ContactsState,
    onEvent: (ContactsEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        ContactsSortType.entries.forEach { sortType ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    onEvent(ContactsEvent.SortContacts(sortType))
                }
            ) {
                RadioButton(
                    selected = state.contactsSortType == sortType,
                    onClick = {
                        onEvent(ContactsEvent.SortContacts(sortType))
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green,
                        unselectedColor = Color.Green
                    )
                )
                Text(
                    text = sortType.name,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ContactsListContent(
    contact: Contact,
    onEvent: (ContactsEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onEvent(ContactsEvent.SetFirstName(contact.firstName))
                onEvent(ContactsEvent.SetSecondName(contact.secondName))
                onEvent(ContactsEvent.SetPhoneNumber(contact.phoneNumber))
                onEvent(ContactsEvent.SetContactID(contact.id))
                onEvent(ContactsEvent.ShowUpdateDialog)
            }
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${contact.firstName} ${contact.secondName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = contact.phoneNumber,
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif
            )
        }
        IconButton(onClick = {
            onEvent(ContactsEvent.DeleteContact(contact))
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete contact"
            )
        }
    }
}