package com.example.jetpack_multiplenavigation.contactsRoom1.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "contacts_table")
data class Contact(
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("second_name")
    val secondName: String,
    @ColumnInfo("phone_number")
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Contact

        if (firstName != other.firstName) return false
        if (secondName != other.secondName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + secondName.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + id
        return result
    }
}
