package com.example.jetpack_multiplenavigation.authentication.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerializedName("data")
    val data: Data
)
