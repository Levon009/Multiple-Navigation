package com.example.jetpack_multiplenavigation.listSwipeDropMenu.data

import kotlinx.serialization.Serializable

@Serializable
data class ProgrammingLanguage(
    val languageName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProgrammingLanguage

        return languageName == other.languageName
    }

    override fun hashCode(): Int {
        return languageName.hashCode()
    }

    override fun toString(): String {
        return "ProgrammingLanguage(languageName='$languageName')"
    }
}
