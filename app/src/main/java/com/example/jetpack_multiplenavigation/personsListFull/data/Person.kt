package com.example.draf.personsListFull.data

data class Person(
    val name: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
