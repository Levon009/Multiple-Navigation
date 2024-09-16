package com.example.jetpack_multiplenavigation.students.studentNavArgs

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.draf.students.data.model.Student
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StudentsNavArgs {
    val StudentsNavType = object : NavType<Student>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Student? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Student {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: Student) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value: Student): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}