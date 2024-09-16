package com.example.jetpack_multiplenavigation.authentication.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerializedName("token")
    val accessToken: String
)
