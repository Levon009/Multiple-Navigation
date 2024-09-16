package com.example.jetpack_multiplenavigation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun notificationChannel(
    context: Context,
    channelId: String,
    channelName: String,
    descriptionText: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            importance
        ).apply {
            description = descriptionText
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}