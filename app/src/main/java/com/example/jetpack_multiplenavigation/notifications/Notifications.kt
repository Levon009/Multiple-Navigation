package com.example.jetpack_multiplenavigation.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import com.example.jetpack_multiplenavigation.MainActivity

fun simpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    contentTitle: String,
    contentText: String,
    smallIcon: Int,
    autoCancel: Boolean,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        PendingIntent.FLAG_IMMUTABLE,
        intent,
        PendingIntent.FLAG_MUTABLE
    )

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(smallIcon)
        .setContentTitle(contentTitle)
        .setContentText(contentText)
        .setContentIntent(pendingIntent)
        .setAutoCancel(autoCancel)
        .setPriority(priority)
        .build()

    notificationManager.notify(notificationId, notification)
}

fun notificationWithLargeText(
    context: Context,
    channelId: String,
    notificationId: Int,
    contentTitle: String,
    contentText: String,
    smallIcon: Int,
    autoCancel: Boolean,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        PendingIntent.FLAG_IMMUTABLE,
        intent,
        PendingIntent.FLAG_MUTABLE
    )

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(smallIcon)
        .setContentTitle(contentTitle)
        .setContentText(contentText)
        .setContentIntent(pendingIntent)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(contentText)
        )
        .setAutoCancel(autoCancel)
        .setPriority(priority)
        .build()

    notificationManager.notify(notificationId, notification)
}

fun notificationLargeTextBigIcon(
    context: Context,
    channelId: String,
    notificationId: Int,
    contentTitle: String,
    contentText: String,
    smallIcon: Int,
    largeIcon: Bitmap,
    autoCancel: Boolean,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        PendingIntent.FLAG_IMMUTABLE,
        intent,
        PendingIntent.FLAG_MUTABLE
    )
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(smallIcon)
        .setContentTitle(contentTitle)
        .setContentText(contentText)
        .setLargeIcon(largeIcon)
        .setContentIntent(pendingIntent)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(contentText)
        )
        .setAutoCancel(autoCancel)
        .setPriority(priority)
        .build()

    notificationManager.notify(notificationId, notification)
}

fun notificationWithBigPicture(
    context: Context,
    channelId: String,
    notificationId: Int,
    contentTitle: String,
    contentText: String,
    smallIcon: Int,
    bigPicture: Bitmap,
    autoCancel: Boolean,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        PendingIntent.FLAG_IMMUTABLE,
        intent,
        PendingIntent.FLAG_MUTABLE
    )

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(smallIcon)
        .setContentTitle(contentTitle)
        .setContentText(contentText)
        .setContentIntent(pendingIntent)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(bigPicture)
        )
        .setAutoCancel(autoCancel)
        .setPriority(priority)
        .build()

    notificationManager.notify(notificationId, notification)
}