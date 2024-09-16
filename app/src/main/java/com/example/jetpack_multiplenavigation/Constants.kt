package com.example.jetpack_multiplenavigation

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringPreferencesKey("AUTH_KEY")
    val emailRegex = Regex("^(?=.*[0-9]?)(?=.*[a-z])(?=.*[A-Z]?)(?=.*[@])(?=.*[.])(?=.*[!@#$%^&*=+]?)(?=\\S+\$).{8,}\$")
    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]?)(?=\\S+\$).{6,}\$".toRegex()
}