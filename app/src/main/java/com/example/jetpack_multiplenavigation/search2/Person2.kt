package com.example.jetpack_multiplenavigation.search2

data class Person2(
    val firstName: String,
    val lastName: String
) {
    fun doesSearchQueryMatch(query: String) : Boolean {
        val matchConditions = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                "${firstName.first()} ${lastName.first()}"
            } else {
                "."
            }
        )

        return matchConditions.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
