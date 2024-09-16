package com.example.jetpack_multiplenavigation.authentication.common

import com.example.jetpack_multiplenavigation.authentication.data.remote.request.AuthRequest

sealed class UIEvents {
    data class SetEmail(val email: String) : UIEvents()
    data class SetPassword(val password: String) : UIEvents()
    data class SetFirstName(val firstName: String) : UIEvents()
    data class SetLastName(val lastName: String) : UIEvents()
    data class LoginUser(val authRequest: AuthRequest) : UIEvents()
    data class RegisterUser(val authRequest: AuthRequest) : UIEvents()
    object GetUser : UIEvents()
    object ShowLoading : UIEvents()
    object HideLoading : UIEvents()
}
