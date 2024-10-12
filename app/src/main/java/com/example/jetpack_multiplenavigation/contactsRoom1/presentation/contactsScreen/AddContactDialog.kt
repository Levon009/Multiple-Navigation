package com.example.jetpack_multiplenavigation.contactsRoom1.presentation.contactsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsEvent
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsState

@Composable
fun AddContactDialog(
    modifier: Modifier = Modifier,
    state: ContactsState,
    onEvent: (ContactsEvent) -> Unit
) {
    val firstName = remember {
        mutableStateOf(state.firstName)
    }
    val secondName = remember {
        mutableStateOf(state.secondName)
    }
    val phoneNumber = remember {
        mutableStateOf(state.phoneNumber)
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactsEvent.HideDialog)
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(ContactsEvent.SaveContact)
            }) {
                Text(
                    text = "Save",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(ContactsEvent.HideDialog)
            }) {
                Text(
                    text = "Dismiss",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        title = {
            Text(
                text = "Add contact",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextFieldContent(
                    text = firstName,
                    placeHolderText = "First name"
                ) {
                    onEvent(ContactsEvent.SetFirstName(firstName.value))
                }
                TextFieldContent(
                    text = secondName,
                    placeHolderText = "Second name"
                ) {
                    onEvent(ContactsEvent.SetSecondName(secondName.value))
                }
                TextFieldContent(
                    text = phoneNumber,
                    placeHolderText = "Phone number"
                ) {
                    onEvent(ContactsEvent.SetPhoneNumber(phoneNumber.value))
                }
            }
        }
    )
}

@Composable
private fun TextFieldContent(
    text: MutableState<String>,
    placeHolderText: String,
    onValueChange: () -> Unit
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChange()
        },
        placeholder = {
            Text(
                text = placeHolderText,
                fontFamily = FontFamily.Serif
            )
        }
    )
}