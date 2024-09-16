package com.example.jetpack_multiplenavigation.permissions

class PermissionTextProviderImpl(
    private val permission: String
) : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined $permission permission. You can go to the app settings to grant it."
        } else {
            "This app needs access to your $permission permission."
        }
    }
}