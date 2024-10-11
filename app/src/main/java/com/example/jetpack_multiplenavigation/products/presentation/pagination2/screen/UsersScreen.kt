package com.example.jetpack_multiplenavigation.products.presentation.pagination2.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpack_multiplenavigation.products.presentation.pagination2.data.model.User
import com.example.jetpack_multiplenavigation.products.presentation.pagination2.employeeViewModel.EmployeeViewModel

@Composable
fun UsersScreen(employeeViewModel: EmployeeViewModel) {
    val users = employeeViewModel.users
    val usersList: LazyPagingItems<User> = users.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(usersList.itemSnapshotList) { user ->
            //.......
        }
    }
}