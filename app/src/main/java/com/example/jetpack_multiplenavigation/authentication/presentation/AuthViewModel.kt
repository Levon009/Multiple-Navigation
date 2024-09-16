package com.example.jetpack_multiplenavigation.authentication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.authentication.common.UIEvents
import com.example.jetpack_multiplenavigation.authentication.data.model.UserDto
import com.example.jetpack_multiplenavigation.authentication.domain.repository.AuthRepository
import com.example.jetpack_multiplenavigation.authentication.utils.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserDto?>(null)
    val user = _user.asStateFlow()

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    private val _errorToastChannel = Channel<Boolean>()
    val errorToastChannel = _errorToastChannel.receiveAsFlow()

    fun onEvent(uiEvents: UIEvents) {
        when(uiEvents) {
            is UIEvents.SetEmail -> {
                _authState.update {
                    authState.value.copy(
                        email = uiEvents.email
                    )
                }
            }
            is UIEvents.SetFirstName -> {
                _authState.update {
                    authState.value.copy(
                        firstName = uiEvents.firstName
                    )
                }
            }
            is UIEvents.SetLastName -> {
                _authState.update {
                    authState.value.copy(
                        lastName = uiEvents.lastName
                    )
                }
            }
            is UIEvents.SetPassword -> {
                _authState.update {
                    authState.value.copy(
                        password = uiEvents.password
                    )
                }
            }
            is UIEvents.LoginUser -> {
                viewModelScope.launch {
                    repository.loginUser(uiEvents.authRequest)
                }
            }
            is UIEvents.RegisterUser -> {
                viewModelScope.launch {
                    repository.registerUser(uiEvents.authRequest)
                }
            }
            UIEvents.GetUser -> {
                viewModelScope.launch {
                    repository.getUser().collectLatest { result ->
                        when(result) {
                            is AuthResult.Error -> {
                                _errorToastChannel.send(true)
                            }
                            is AuthResult.Success -> {
                                result.data?.let { userDto ->
                                   _user.update { userDto }
                                }
                            }
                        }
                    }
                }
            }
            UIEvents.ShowLoading -> {
                _authState.update {
                    authState.value.copy(
                        isLoading = true
                    )
                }
            }
            UIEvents.HideLoading -> {
                _authState.update {
                    authState.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}