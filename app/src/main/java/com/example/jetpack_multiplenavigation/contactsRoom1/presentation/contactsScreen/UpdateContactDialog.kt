package com.example.jetpack_multiplenavigation.contactsRoom1.presentation.contactsScreen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsEvent
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.ContactsState
import com.example.jetpack_multiplenavigation.contactsRoom1.domain.model.Contact

@Composable
fun UpdateContactDialog(
    modifier: Modifier = Modifier,
    state: ContactsState,
    onEvent: (ContactsEvent) -> Unit
) {
    val context = LocalContext.current
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
            onEvent(ContactsEvent.HideUpdateDialog)
        },
        confirmButton = {
            TextButton(onClick = {
                if (firstName.value.isNotBlank() || secondName.value.isNotBlank() || phoneNumber.value.isNotBlank()) {
                    val contact = Contact(firstName.value, secondName.value, phoneNumber.value)
                    onEvent(ContactsEvent.UpdateContact(contact))
                } else {
                    Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(
                    text = "Save",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(ContactsEvent.HideUpdateDialog)
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
        },
        isError = text.value.isBlank(),
        supportingText = {
            Text(text = "*requires")
        }
    )
}