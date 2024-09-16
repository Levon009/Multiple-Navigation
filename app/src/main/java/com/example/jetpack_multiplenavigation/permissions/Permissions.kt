package com.example.jetpack_multiplenavigation.permissions

import android.Manifest

object Permissions {
    const val POST_NOTIFICATION_R_CODE = 1

    val permissionsList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE
    )
}