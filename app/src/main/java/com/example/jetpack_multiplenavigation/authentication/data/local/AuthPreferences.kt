package com.example.jetpack_multiplenavigation.authentication.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.jetpack_multiplenavigation.authentication.core.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(
    private val context: Context
) {

    val getToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[Constants.AUTH_KEY] ?: ""
    }

    suspend fun saveAuthToken(accessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[Constants.AUTH_KEY] = accessToken
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(Constants.AUTH_PREFERENCES)
    }
}