package com.example.jetpack_multiplenavigation.authentication.data.remote.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
