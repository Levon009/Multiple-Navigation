package com.example.jetpack_multiplenavigation.permissions

import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PermissionsViewModel : ViewModel() {

    var visiblePermissionsRequestQueue = mutableStateListOf<String>()

    @RequiresApi(35)
    fun dismissPermission() {
        visiblePermissionsRequestQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !visiblePermissionsRequestQueue.contains(permission)) {
            visiblePermissionsRequestQueue.add(permission)
        }
    }
}