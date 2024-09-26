package com.example.jetpack_multiplenavigation.swipeWithActions.data

import androidx.compose.runtime.toMutableStateList
import com.example.jetpack_multiplenavigation.swipeWithActions.data.model.SwipeAbleContacts

val allSwipeAbleContacts = (1..100).map {
    SwipeAbleContacts(
        id = it,
        name = "Person $it",
        isOptionalRevealed = false
    )
}.toMutableStateList()