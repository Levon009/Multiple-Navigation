package com.example.jetpack_multiplenavigation.pet.data

import androidx.annotation.DrawableRes

data class Pet(
    val id: Int,
    val name: String,
    val age: String,
    val breed: String,
    val gender: String,
    val color: String,
    val location: String,
    val description: String,
    @DrawableRes val image: Int
)
