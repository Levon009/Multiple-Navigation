package com.example.jetpack_multiplenavigation.customNavArgs

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.Dog
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavArgs {
    val DogType = object : NavType<Dog>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Dog? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Dog {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: Dog) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value: Dog): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}