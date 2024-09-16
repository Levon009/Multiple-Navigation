package com.example.jetpack_multiplenavigation.dependencies.data

import android.content.Context

class DbClient(
    private val context: Context
) {
    fun helloLevon() : String {
        return "Hello Levon"
    }
}