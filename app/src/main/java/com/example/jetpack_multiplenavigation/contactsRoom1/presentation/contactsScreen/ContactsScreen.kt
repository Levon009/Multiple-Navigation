package com.example.jetpack_multiplenavigation.contactsRoom1.presentation.contactsScreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.contactsRoom1.presentation.ContactsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactsScreen(navController: NavHostController) {
    val contactsViewModel = koinViewModel<ContactsViewModel>()
    val state = contactsViewModel.state.collectAsStateWithLifecycle().value
    ContactsListScreen(
        navController = navController,
        state = state
    ) {
        contactsViewModel.onEvent(it)
    }
}