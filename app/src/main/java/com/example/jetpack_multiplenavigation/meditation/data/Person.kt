package com.example.jetpack_multiplenavigation.meditation.data

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val firstName: String,
    val lastName: String
) {
    fun doesMatchSearchQuery(query: String) : Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                "${firstName.first()} ${lastName.first()}"
            } else {
                "."
            }
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
