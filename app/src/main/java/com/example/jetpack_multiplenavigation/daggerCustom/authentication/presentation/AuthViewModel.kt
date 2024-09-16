package com.example.jetpack_multiplenavigation.daggerCustom.authentication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.model.LoginRequest
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(
        loginRequest: LoginRequest
    ) {
        viewModelScope.launch {
            authRepository.login(loginRequest.email, loginRequest.password)
        }
    }
}