package com.example.jetpack_multiplenavigation.products.retrofit.pagination2.employeeViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack_multiplenavigation.products.retrofit.pagination2.data.model.User
import com.example.jetpack_multiplenavigation.products.retrofit.pagination2.usersPagination.UsersPagination
import kotlinx.coroutines.flow.Flow

class EmployeeViewModel : ViewModel() {
    val users: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 1)) {
       UsersPagination()
    }.flow.cachedIn(viewModelScope)
}