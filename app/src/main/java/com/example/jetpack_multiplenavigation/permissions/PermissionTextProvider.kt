package com.example.jetpack_multiplenavigation.permissions

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean) : String
}